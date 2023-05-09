package com.lee.automatic.weixin.model.interact;

import lombok.Data;

/**
 * 机器人消息接收消息结构体
 *
 * @author Lee
 */
@Data
public class ReceiveMsgEncryptResp {

    /**
     * 企业微信的CorpID，当为第三方套件回调事件时，CorpID的内容为suiteid
     */
    private String random;
    /**
     * 接收的应用id，可在应用的设置页面获取
     */
    private String msg_len;
    /**
     * 消息结构体加密后的字符串
     */
    private String msg;
    /**
     * 消息结构体加密后的字符串
     */
    private String receiveid;
}