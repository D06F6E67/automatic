package com.lee.automatic.pudn.model;

import lombok.Data;

/**
 * pudn金币信息相应
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
