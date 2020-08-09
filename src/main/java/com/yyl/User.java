package com.yyl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

/**
 * @Author: yyl
 * @Date: 2018/11/27 19:51
 */
@Getter
@Setter
@ToString
public class User {
    private String id;
    private String name;
    private int age;
    private Date birthday;
}
