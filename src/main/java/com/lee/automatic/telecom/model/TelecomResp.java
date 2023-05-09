package com.lee.automatic.telecom.model;

import lombok.Data;

/**
 * 电信云宠物 返回结果
 *
 * @author Lee
 */
@Data
public class TelecomResp<T> {

    /**
     * 代码
     */
    private Integer code;
    /**
     * 信息
     */
    private String message;
    /**
     * 标记
     */
    private String flag;
    /**
     * 结果
     */
    private T result;
    /**
     * 操作对象
     */
    private String operationObject;
    /**
     * 成功
     */
    private Boolean success;
}