package com.lee.automatic.pudn.model;

import lombok.Data;

/**
 * pudn 登陆请求
 *
 * @author Lee
 */
@Data
public class LoginReq {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 登陆类型
     */
    private Integer loginType;

    public LoginReq(String username, String password) {
        this.username = username;
        this.password = password;
        this.loginType = 0;
    }
}
