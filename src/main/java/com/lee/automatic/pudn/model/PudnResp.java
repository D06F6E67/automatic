package com.lee.automatic.pudn.model;

import lombok.Data;

/**
 * pudn响应
 *
 * @author Lee
 */
@Data
public class PudnResp<T> {

    /**
     * 代码
     */
    private Integer code;
    /**
     * 信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

}
