package com.lee.automatic.weixin.model;

import lombok.Data;

/**
 * 微信响应
 *
 * @author Lee
 */
@Data
public class WeiXinResp {

    /**
     * 错误代码
     */
    private Integer errcode;
    /**
     * 错误信息
     */
    private String errmsg;
    /**
     * 授权token，仅获取token接口使用
     */
    private String accessToken;
}