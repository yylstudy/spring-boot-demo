package com.yyl.dao;

import com.yyl.domain.User;
import com.yyl.annotation.Dao;

import java.util.List;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl.dao
 * @Description: TODO(这里描述)
 * @Date 2019/7/20 0020
 */
@Dao
public interface UserDao {
    List<User> findList();
    int update();
}
