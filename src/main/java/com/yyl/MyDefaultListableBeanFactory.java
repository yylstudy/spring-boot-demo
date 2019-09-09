package com.yyl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Author yang.yonglian
 * @ClassName: com.ztesoft.list
 * @Description: TODO(这里描述)
 * @Date 2019/6/10 0010
 */
public class MyDefaultListableBeanFactory extends DefaultListableBeanFactory {
    private Logger logger = LoggerFactory.getLogger(MyDefaultListableBeanFactory.class);

    public MyDefaultListableBeanFactory() {
    }

    @Override
    public Object getBean(String name) throws BeansException {
        long t1 = System.currentTimeMillis();
        Object obj = super.getBean(name);
        long t2 = System.currentTimeMillis();
        logger.warn("实例化 "+name+" 耗时："+(t2-t1));
        return obj;
    }
}
