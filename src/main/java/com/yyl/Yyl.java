package com.yyl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/20 0020
 */
@ConfigurationProperties("yyl")
@Component
@Getter
@Setter
@ToString
public class Yyl {
    private String age;
    private String height;
}
