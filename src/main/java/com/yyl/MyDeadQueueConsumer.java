package com.yyl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: 死信队列重试消费者，重试消费次数达到10次，就不消费了，插入数据库记录等待人工处理
 * @Date 2019/8/30 0030
 */
@Component
@Slf4j
@RabbitListener(queues = RabbitMqConstant.DEAD_QUEUE_NAME)
public class MyDeadQueueConsumer {
    @Autowired
    private RabbitmqMsgDao rabbitmqMsgDao;

    @RabbitHandler
    @SneakyThrows(Exception.class)
    public void consumerDeadMessage(MyMsg myMsg, Message message, Channel channel){
        try{
            log.debug("接收到消息:{}", myMsg);
            int s = 1/0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            try{
                long retryCount = getRetryCount(message);
                //这里不用手动修改消息头部的count属性值，因为这里是重新发送到延迟队列，延迟队列上没有消费者
                //经过消息对应的过期时间后，变成死信进入死信队列，在进入死信队列的时候，count属性会自动加1
                //这个在MyNormalQueueConsumer中消息进入死信也可以看到
                log.debug("consume message time:{}", retryCount);
                channel.confirmSelect();
                if(retryCount<10){
                    channel.basicPublish(RabbitMqConstant.DELAY_EXCHANGE_NAME,RabbitMqConstant.NORMAL_ROUTING_KEY,
                            converBasicProperties(message.getMessageProperties(),getExpiration(retryCount)) , message.getBody());
                    if(channel.waitForConfirms()){
                        log.info("message:{} ack success",message.getMessageProperties().getDeliveryTag());
                        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                    }else{
                        log.info("message:{} uack success",message.getMessageProperties().getDeliveryTag());
                        channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
                    }
                }else{
                    RabbitmqMsg rabbitmqMsg = new RabbitmqMsg();
                    rabbitmqMsg.setCreateDate(new Date());
                    rabbitmqMsg.setMsgId(myMsg.getId());
                    rabbitmqMsg.setMsgJson(JSON.toJSONString(myMsg));
                    try{
                        int insertCount = rabbitmqMsgDao.insert(rabbitmqMsg);
                        if(insertCount==1){
                            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                        }else{
                            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
                        }
                    }catch (DuplicateKeyException de){
                        log.warn("msg is already exists:"+myMsg.getId());
                        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                    }
                }
            }catch (Exception ee){
                log.error("consumer msg failure",ee);
                throw new RuntimeException(ee);
            }
        }

    }

    /**
     * 转换BasicProperties对象
     * @param messageProperties
     * @param expiration
     * @return
     */
    private AMQP.BasicProperties converBasicProperties(MessageProperties messageProperties,String expiration) {
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties(
                messageProperties.getContentType(),
                messageProperties.getContentEncoding(),
                messageProperties.getHeaders(),
                messageProperties.getDelay(),
                messageProperties.getPriority(),
                messageProperties.getCorrelationIdString(),
                messageProperties.getReplyTo(),
                expiration,
                messageProperties.getMessageId(),
                messageProperties.getTimestamp(),
                messageProperties.getType(),
                messageProperties.getUserId(),
                messageProperties.getAppId(),
                messageProperties.getClusterId()
        );
        return basicProperties;
    }


    /**
     * 获取重试次数
     * @param message
     * @return
     */
    private long getRetryCount(Message message){
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        if (headers != null) {
            if (headers.containsKey("x-death")) {
                List<Map<String, Object>> deaths = (List<Map<String, Object>>) headers.get("x-death");
                if (deaths.size() > 0) {
                    Map<String, Object> death = deaths.get(0);
                    return (Long) death.get("count");
                }
            }
        }
        return 0;
    }

    private String getExpiration(long retryCount){
        return String.valueOf(retryCount*2*1000);
    }

}
