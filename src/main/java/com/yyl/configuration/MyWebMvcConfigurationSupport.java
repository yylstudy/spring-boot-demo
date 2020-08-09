package com.yyl.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * spring boot中自定义RequestMappingHandlerMapping 需要重写WebMvcConfigurationSupport
 * 这样spring boot中就只有5种RequestMapping 就不会存在SimpleUrlHandlerMapping
 * 否则，自定义的RequestMapping 会在SimpleUrlHandlerMapping之后解析，导致无法找到报错
 * 或者使用EnableWebMvc注解，覆盖springboot 默认的spring mvc的配置
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/16 0016
 */
@Configuration
public class MyWebMvcConfigurationSupport extends WebMvcConfigurationSupport {
    @Override
    protected void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

        returnValueHandlers.add(new MyHandlerMethodReturnValueHandler(this.getMessageConverters()));
    }
}
