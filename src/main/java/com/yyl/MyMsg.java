package com.yyl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/15 0015
 */
@Setter
@Getter
@ToString(callSuper=true)
public class MyMsg extends BaseEntry  {
    private String name;
}
