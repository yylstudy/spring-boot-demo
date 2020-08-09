package com.yyl.rabbitmq;

import com.yyl.domain.BaseEntry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/15 0015
 */
@Setter
@Getter
@ToString(callSuper=true)
public class MyMsg extends BaseEntry {
    private String name;
}
