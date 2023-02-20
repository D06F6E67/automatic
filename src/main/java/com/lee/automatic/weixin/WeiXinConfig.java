package com.lee.automatic.weixin;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 企业微信 配置信息
 *
 * @author Lee
 */
@Data
@Configuration
@ConfigurationProperties("weixin")
public class WeiXinConfig {

    /**
     * 企业ID
     */
    private String corpId;
    /**
     * 企业应用ID
     */
    private Integer[] agentId;
    /**
     * 应用密钥
     */
    private String[] corpsecret;



    /**
     * 获取access_token
     */
    public static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    /**
     * 发送应用消息
     */
    public static final String SEND_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

}
