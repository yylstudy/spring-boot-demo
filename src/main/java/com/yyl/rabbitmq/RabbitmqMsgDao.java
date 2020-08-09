package com.yyl.rabbitmq;

import com.yyl.annotation.Dao;
import com.yyl.rabbitmq.RabbitmqMsg;
import org.springframework.context.annotation.Profile;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/30 0030
 */
@Dao
@Profile("rabbitmq")
public interface RabbitmqMsgDao {
    int insert(RabbitmqMsg msg);
}
