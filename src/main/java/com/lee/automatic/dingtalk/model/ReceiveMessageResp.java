package com.lee.automatic.dingtalk.model;

import lombok.Data;

import java.util.List;

/**
 * 机器人消息接收
 *
 * @author Lee
 */
@Data
public class ReceiveMessageResp {

    /**
     * 会话ID
     */
    private String conversationId;
    /**
     * 群聊时才有的会话标题
     */
    private String conversationTitle;
    /**
     * 被@人的信息
     */
    private List<AtUsers> atUsers;
    /**
     * 加密的机器人所在的企业corpId
     */
    private String chatbotCorpId;
    /**
     * 加密的机器人ID
     */
    private String chatbotUserId;
    /**
     * 加密的消息ID
     */
    private String msgId;
    /**
     * 加密的发送者ID
     */
    private String senderId;
    /**
     * 发送者昵称
     */
    private String senderNick;
    /**
     * 是否为管理员
     */
    private Boolean isAdmin;
    /**
     * 企业内部群中@该机器人的成员userid
     */
    private String senderStaffId;
    /**
     * 消息的时间戳，单位ms
     */
    private Long createAt;
    /**
     * 企业内部群有的发送者当前群的企业corpId
     */
    private String senderCorpId;
    /**
     * 1：单聊
     * 2：群聊
     */
    private String conversationType;
    /**
     * 是否在@列表中
     */
    private Boolean isInAtList;
    /**
     * 当前会话的Webhook地址
     */
    private String sessionWebhook;
    /**
     * 当前会话的Webhook地址过期时间
     */
    private Long sessionWebhookExpiredTime;
    /**
     * 消息
     */
    private Text text;
    /**
     * 目前只支持text
     */
    private String msgtype;

    /**
     * 消息
     */
    @Data
    public static class Text {
        /**
         * 消息文本
         */
        private String content;
    }

    /**
     * 被@人的信息
     */
    @Data
    public static class AtUsers {
        /**
         * 加密的发送者ID
         */
        private String dingtalkId;
        /**
         * 企业内部群有的发送者在企业内的userid
         */
        private String staffId;
    }
}
