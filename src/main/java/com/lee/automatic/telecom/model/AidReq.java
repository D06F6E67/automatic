package com.lee.automatic.telecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 电信云宠物 aid 请求参数
 *
 * @author Lee
 */
@Data
@AllArgsConstructor
public class AidReq {

    /**
     * aid
     */
    private String aid;
}