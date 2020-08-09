package com.yyl.annotation;

import java.lang.annotation.*;

/**
 * @Author: yang.yonglian
 * @Description:
 * @Date: Create in 2020/4/30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestResponseBody {

}
