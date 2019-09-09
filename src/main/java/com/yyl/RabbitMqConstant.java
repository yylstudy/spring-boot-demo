package com.yyl;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/15 0015
 */
public class RabbitMqConstant {
    /**正常交换器*/
    public static final String NORMAL_EXCHANGE_NAME = "normal_exchange_test";
    /**正常队列*/
    public static final String NORMAL_QUEUE_NAME = "normal_queue_test";
    /**正常路由键*/
    public static final String NORMAL_ROUTING_KEY = "normal_rk";
    /**备份交换器*/
    public static final String AE_EXCHANGE_NAME = "ae_exchange_test";
    /**备份交换器上的队列*/
    public static final String AE_QUEUE_NAME = "ae_queue_test";
    /**延迟交换器*/
    public static final String DELAY_EXCHANGE_NAME = "delay_exchange_test";
    /**延迟队列*/
    public static final String DELAY_QUEUE_NAME = "delay_queue_test";
    /**死信交换器*/
    public static final String DEAD_EXCHANGE_NAME = "dead_exchange_test";
    /**死信队列*/
    public static final String DEAD_QUEUE_NAME = "dead_queue_test";


}
