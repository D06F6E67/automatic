package com.lee.automatic.weixin.model.send;

import com.lee.automatic.weixin.RobotEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 微信发送文本消息 请求
 * <pre>
 *     <a href="https://developer.work.weixin.qq.com/document/path/90236#%E6%96%87%E6%9C%AC%E6%B6%88%E6%81%AF">微信文档</a>
 * </pre>
 *
 * @author Lee
 */
@Data
public class TextMessageReq extends MessageReq {

    /**
     * 消息
     */
    private WeiXinText text;

    public TextMessageReq(String touser, RobotEnum which, String text) {
        super(touser, "text", which);
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
