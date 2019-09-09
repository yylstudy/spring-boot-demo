package com.yyl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/30 0030
 */
@Setter
@Getter
@ToString
public class RabbitmqMsg implements Serializable {
    private String msgId;
    private String msgJson;
    private Date createDate;
}
