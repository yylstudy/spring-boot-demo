package com.yyl;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: 消费者
 * @Date 2019/8/15 0015
 */
@Component
@RabbitListener(queues = RabbitMqConstant.NORMAL_QUEUE_NAME)
public class MyNormalQueueConsumer {
    private Logger log = LoggerFactory.getLogger(MyNormalQueueConsumer.class);

    /**
     * 采用@RabbitListener和@RabbitHandler结合的方式进行对消费方的消息确认
     * @param myMsg 消息实体
     * @param channel 信道，可用此对象进行手动ack或者nack
     * @param message 消息属性，包含ack中需要的消息唯一标识
     */
    @RabbitHandler
    @SneakyThrows(Exception.class)
    public void consumeMsg(MyMsg myMsg,Channel channel,Message message){
        log.debug("接收到消息:{}", myMsg);
        try{
            int s = 1/0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            //拒绝消息，消息会进入死信队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }
    }



}
