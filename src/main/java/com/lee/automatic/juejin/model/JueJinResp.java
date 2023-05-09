package com.lee.automatic.juejin.model;

import lombok.Data;

/**
 * 掘金响应
 *
 * @author Lee
 */
@Data
public class JueJinResp<T> {

    /**
     * 错误代码
     */
    private Integer errNo;
    /**
     * 错误信息
     */
    private String errMsg;
    /**
     * 响应数据
     */
    private T data;
}