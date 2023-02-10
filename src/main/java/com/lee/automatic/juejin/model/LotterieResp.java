package com.lee.automatic.juejin.model;

import lombok.Data;

/**
 * 大奖信息
 *
 * @author Lee
 */
@Data
public class LotterieResp {

    /**
     * 获奖用户ID
     */
    String userId;
    /**
     * 获奖ID
     */
    String historyId;
    /**
     * 获奖用户名
     */
    String userName;
}
