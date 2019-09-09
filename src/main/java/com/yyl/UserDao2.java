package com.yyl;

import java.util.List;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl.dao
 * @Description: TODO(这里描述)
 * @Date 2019/7/20 0020
 */
@Dao
public interface UserDao2 {
    List<User> findList();
    int update();
}
