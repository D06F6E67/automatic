package com.lee.automatic.pudn.model;

import lombok.Data;

/**
 * pudn 金币信息响应
 *
 * @author Lee
 */
@Data
public class GoldInfoResp {

    /**
     * 金币数量
     */
    private Integer goldSum;
    /**
     * 金币到期时间
     */
    private String goldExpirationMsg;
}