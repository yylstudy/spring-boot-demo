package com.yyl.service;

import com.yyl.annotation.CloudService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/17 0017
 */
@Component
@ConditionalOnProperty(prefix = "cloud",name = "version",havingValue = "2.0")
public class MyCloudService2 implements CloudService {
    @Override
    public void test1() {
        System.out.println("this is MyCloudService2");
    }
}
