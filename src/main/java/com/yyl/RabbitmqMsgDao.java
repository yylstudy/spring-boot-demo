package com.yyl;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/30 0030
 */
@Dao
public interface RabbitmqMsgDao {
    int insert(RabbitmqMsg msg);
}
