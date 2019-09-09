package com.yyl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/15 0015
 */
@Component
@Slf4j
public class MyProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 添加发送消息确认机制
     */
    @PostConstruct
    public void addConfirmCallback(){
        rabbitTemplate.setConfirmCallback((correlationData,ack,cause)->{
            String id = correlationData.getId();
            if(ack){
                log.debug("消息id:{}被确认送达",id);
            }else{
                log.debug("消息id:{}未送达mq,原因:{}",id,cause);
            }
        });
    }

    public void sendMsg(MyMsg myMsg){
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(myMsg.getId());
        rabbitTemplate.convertAndSend(RabbitMqConstant.NORMAL_EXCHANGE_NAME,
                RabbitMqConstant.NORMAL_ROUTING_KEY, myMsg,correlationData );
    }
}
