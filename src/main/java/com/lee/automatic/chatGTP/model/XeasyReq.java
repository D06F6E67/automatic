package com.lee.automatic.chatGTP.model;

import com.lee.automatic.common.utils.DateUtils;
import com.lee.automatic.common.utils.EncryptionUtils;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * xeasy 页面请求参数
 *
 * @author Lee
 */
@Data
public class XeasyReq {

    /**
     * 消息列表
     */
    private List<Messages> messages;
    /**
     * key
     */
    private String key;
    /**
     * 时间戳
     */
    private Long time;
    /**
     * 加密
     */
    private String sign;

    public XeasyReq(String content) {
        this.messages = Collections.singletonList(new Messages(content));
        time = DateUtils.getTimeMillis();
        sign = EncryptionUtils.SHA256(String.format("%s:%s:undefined", time, content));
    }

    /**
     * Messages
     */
    @Data
    public static class Messages {
        /**
         * 角色
         */
        private String role;
        /**
         * 消息
         */
        private String content;

        public Messages(String content) {
            role = "user";
            this.content = content;
        }
    }
}