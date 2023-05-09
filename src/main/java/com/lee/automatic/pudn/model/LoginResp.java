package com.lee.automatic.pudn.model;

import lombok.Data;

/**
 * pudn 登陆响应
 *
 * @author Lee
 */
@Data
public class LoginResp {

    /**
     * 成员 ID 字符串
     */
    private String memberIdStr;
    /**
     * token
     */
    private String token;
    /**
     * 刷新令牌
     */
    private Object refreshToken;
    /**
     * 令牌头
     */
    private Object tokenHead;
    /**
     * 过期日期
     */
    private Object expiresIn;
    /**
     * 用户名
     */
    private String username;
    /**
     * 电话
     */
    private String phone;
    /**
     * 昵称
     */
    private Object nickname;
    /**
     * 邮箱
     */
    private Object email;
    /**
     * 评分
     */
    private Object score;
    /**
     * vip类型
     */
    private String vipType;
    /**
     * vip时间结束
     */
    private Object vipEndTime;
    /**
     * 初始化用户名
     */
    private Boolean initUsername;
    /**
     * 绑定电话
     */
    private Boolean bindingPhone;
}