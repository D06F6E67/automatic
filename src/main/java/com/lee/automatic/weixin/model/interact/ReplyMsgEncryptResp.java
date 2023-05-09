package com.lee.automatic.weixin.model.interact;

import lombok.Builder;
import lombok.Data;

/**
 * 机器人消息回复消息结构体
 *
 * @author Lee
 */
@Data
@Builder
public class ReplyMsgEncryptResp {

    /**
     * 成员UserID
     */
    private String toUserName;
    /**
     * 企业微信CorpID
     */
    private String fromUserName;
    /**
     * 消息创建时间（整型）
     */
    private String createTime;
    /**
     * 消息类型，text文本
     */
    private String msgType;
    /**
     * 文本消息内容,最长不超过2048个字节，超过将截断
     */
    private String content;

    public String xml() {
        String xml = "<xml>\n" +
                "   <ToUserName><![CDATA[%s]]></ToUserName>\n" +
                "   <FromUserName><![CDATA[%s]]></FromUserName> \n" +
                "   <CreateTime>%s</CreateTime>\n" +
                "   <MsgType><![CDATA[%s]]></MsgType>\n" +
                "   <Content><![CDATA[%s]]></Content>\n" +
                "</xml>";
        return String.format(xml, toUserName, fromUserName, createTime, msgType, content);
    }
}