package com.lee.automatic.weixin.model.interact;

import lombok.Data;

/**
 * 机器人消息回复
 *
 * @author Lee
 */
@Data
public class ReplyMessageReq {

    /**
     * 经过加密的消息结构体
     */
    private String encrypt;
    /**
     * 消息签名
     */
    private String msgSignature;
    /**
     * 时间戳
     */
    private String timeStamp;
    /**
     * 随机数，由企业自行生成
     */
    private String nonce;
}