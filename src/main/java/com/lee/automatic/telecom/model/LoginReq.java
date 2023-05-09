package com.lee.automatic.telecom.model;

import lombok.Data;

/**
 * 电信云宠物 登陆 请求参数
 *
 * @author Lee
 */
@Data
public class LoginReq {

    private String from;
    private String code;
    private String channelId;
    private String posId;
    private String aid;

    public LoginReq(String code, String aid) {
        this.from = "WEITING";
        this.code = code;
        this.channelId = "52";
        this.posId = "3-0-52";
        this.aid = aid;
    }
}