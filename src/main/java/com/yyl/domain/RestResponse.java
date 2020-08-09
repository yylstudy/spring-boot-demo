package com.yyl.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * @Author: yang.yonglian
 * @Description:
 * @Date: Create in 2020/4/27
 */
@Getter
@Setter
@AllArgsConstructor
public class RestResponse<T>  {
    private T content;
    private String code;
}
