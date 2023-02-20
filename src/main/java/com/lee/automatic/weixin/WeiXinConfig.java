package com.lee.automatic.weixin;

import com.lee.automatic.weixin.aes.AesException;
import com.lee.automatic.weixin.aes.WXBizMsgCrypt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 企业微信 配置信息
 *
 * @author Lee
 */
@Data
@Slf4j
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
     * 接收消息配置的token
     */
    private String token;
    /**
     * 接收消息配置的密钥
     */
    private String encodingAESKey;
    /**
     * 接收消息加解密对象
     */
    private WXBizMsgCrypt msgCrypt;

    public WXBizMsgCrypt getMsgCrypt() {
        if (msgCrypt == null) {
            try {
                msgCrypt = new WXBizMsgCrypt(token, encodingAESKey, corpId);
            } catch (AesException e) {
                log.error("微信生成接收消息加解密对象异常", e);
            }
        }

        return msgCrypt;
    }

    /**
     * 获取access_token
     */
    public static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    /**
     * 发送应用消息
     */
    public static final String SEND_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

}
