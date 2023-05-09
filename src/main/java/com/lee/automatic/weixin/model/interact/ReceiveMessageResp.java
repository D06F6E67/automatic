package com.lee.automatic.weixin.model.interact;

import lombok.Data;

/**
 * 机器人消息接收
 *
 * @author Lee
 */
@Data
public class ReceiveMessageResp {

    /**
     * 企业微信的CorpID，当为第三方套件回调事件时，CorpID的内容为suiteid
     */
    private String toUserName;
    /**
     * 接收的应用id，可在应用的设置页面获取
     */
    private String agentId;
    /**
     * 消息结构体加密后的字符串
     */
    private String encrypt;
}