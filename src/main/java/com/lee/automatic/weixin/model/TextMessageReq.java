package com.lee.automatic.weixin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信发送消息 请求
 * <pre><a href="https://developer.work.weixin.qq.com/document/path/90854">微信文档</a></pre>
 *
 * @author Lee
 */
@Data
@NoArgsConstructor
public class TextMessageReq {

    /**
     * 接收消息的成员
     */
    private String touser;
    /**
     * 消息类型
     */
    private String msgtype;
    /**
     * 企业应用的id
     */
    private Integer agentid;
    /**
     * 消息
     */
    private WeiXinText text;

    public TextMessageReq(Integer agentid, String text) {
        this.touser = "@all";
        this.msgtype = "text";
        this.agentid = agentid;
        this.text = new WeiXinText(text);
    }
}

@Data
@AllArgsConstructor
class WeiXinText {
    /**
     * 消息内容
     */
    private String content;
}
