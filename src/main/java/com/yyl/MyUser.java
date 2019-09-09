package com.yyl;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/10 0010
 */
@Component
@ConfigurationProperties("user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
/**
 * spring boot 可以校验@ConfigurationProperties类，但是需要类上标注了@Validated注解
 * 如果配置了，那么就可以在类中直接使用JSR-303
 */
@Validated
public class MyUser {
    @NotNull
    private String age;
    private String name;
}
