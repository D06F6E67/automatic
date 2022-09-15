package com.lee.automatic.weixin.model;

import com.lee.automatic.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信发送文本卡片消息 请求
 * <pre>
 *     <a href="https://developer.work.weixin.qq.com/document/path/90236#%E6%96%87%E6%9C%AC%E5%8D%A1%E7%89%87%E6%B6%88%E6%81%AF">微信文档</a>
 * </pre>
 *
 * @author Lee
 */
@Data
@NoArgsConstructor
public class TextCardMessageReq extends MessageReq {

    /**
     * 消息
     */
    private WeiXinTextCar textcard;

    public TextCardMessageReq(String description) {
        super();
        this.textcard = new WeiXinTextCar(description);
    }
}

@Data
@AllArgsConstructor
class WeiXinTextCar {
    /**
     * 标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String description;
    /**
     * 点击后跳转的链接
     */
    private String url;

    public WeiXinTextCar(String description) {
        this.title = DateUtils.todayDateTime();
        this.description = description;
        this.url = "URL";
    }

}
