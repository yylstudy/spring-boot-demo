package com.yyl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/16 0016
 */
@Aspect
@Component
public class MyAspect {

    @Before("execution(* com.yyl.MyController.test2(..))")
    public void test1(){
        System.out.println("hhahahah");
    }
}
