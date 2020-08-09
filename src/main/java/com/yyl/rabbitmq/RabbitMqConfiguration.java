package com.yyl.rabbitmq;

import com.yyl.rabbitmq.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/15 0015
 */
@Profile("rabbitmq")
@Configuration
@Slf4j
public class RabbitMqConfiguration {

    /**
     * 备份交换器
     * @return
     */
    @Bean
    public Exchange aeExchange(){
        return new FanoutExchange(RabbitMqConstant.AE_EXCHANGE_NAME, true, false);
    }

    /**
     * 备份交换器上的队列
     * @return
     */
    @Bean
    public Queue aeQueue(){
        return new Queue(RabbitMqConstant.AE_QUEUE_NAME, true, false, false);
    }

    /**
     * 绑定备份交换器和备份队列
     * @return
     */
    @Bean
    public Binding aeBinding(){
        return BindingBuilder.bind(aeQueue()).to(aeExchange()).with("").noargs();
    }

    /**
     * 交换器
     * @return
     */
    @Bean
    public DirectExchange exchange(){
        Map<String,Object> param = new HashMap();
        param.put("alternate-exchange", RabbitMqConstant.AE_EXCHANGE_NAME);
        return new DirectExchange(RabbitMqConstant.NORMAL_EXCHANGE_NAME,true,false,param);
    }

    /**
     * 队列
     * @return
     */
    @Bean
    public Queue queue(){
        Map<String,Object> param = new HashMap(4);
        param.put("x-dead-letter-exchange",RabbitMqConstant.DEAD_EXCHANGE_NAME);
        param.put("x-dead-letter-routing-key",RabbitMqConstant.NORMAL_ROUTING_KEY);
        return new Queue(RabbitMqConstant.NORMAL_QUEUE_NAME, true, false, false,param);
    }

    /**
     * 绑定交换器和队列
     * @return
     */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange())
                .with(RabbitMqConstant.NORMAL_ROUTING_KEY);
    }

    /**
     * 死信交换器
     * @return
     */
    @Bean
    public DirectExchange deadExchange(){
        return new DirectExchange(RabbitMqConstant.DEAD_EXCHANGE_NAME, true, false);
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    public Queue deadQueue(){
        return new Queue(RabbitMqConstant.DEAD_QUEUE_NAME, true, false, false);
    }

    /**
     * 死信交换器绑定
     * @return
     */
    @Bean
    public Binding deadBinding(){
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(RabbitMqConstant.NORMAL_ROUTING_KEY);
    }

    /**
     * 延迟交换器
     * @return
     */
    @Bean
    public DirectExchange delayExchange(){
        return new DirectExchange(RabbitMqConstant.DELAY_EXCHANGE_NAME,true,false);
    }

    /**
     * 延迟队列，此队列上无消费者
     * @return
     */
    @Bean
    public Queue delayQueue(){
        Map<String,Object> param = new HashMap(4);
        param.put("x-dead-letter-exchange",RabbitMqConstant.DEAD_EXCHANGE_NAME);
        param.put("x-dead-letter-routing-key",RabbitMqConstant.NORMAL_ROUTING_KEY);
        return new Queue(RabbitMqConstant.DELAY_QUEUE_NAME, true, false, false,param);
    }

    /**
     * 绑定延迟交换器和延迟队列
     * @return
     */
    @Bean
    public Binding delayBinding(){
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(RabbitMqConstant.NORMAL_ROUTING_KEY);
    }

    /**
     * 消息序列化器
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
