package com.lee.automatic.telecom.model;

import lombok.Data;

/**
 * 电信云宠物 查询签到天数 返回结果
 *
 * @author Lee
 */
@Data
public class QueryCheckInResp {
    /**
     * 连续性登录天数
     */
    private Integer continuitySignInDays;
    /**
     * 总登录天数
     */
    private Integer totalSignInDays;
    /**
     * 登入
     */
    private Boolean signIn;
}