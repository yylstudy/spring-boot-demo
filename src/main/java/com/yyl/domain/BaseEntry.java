package com.yyl.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/15 0015
 */
@Setter
@Getter
public class BaseEntry implements Serializable {
    @NotNull
    private String id;
}
