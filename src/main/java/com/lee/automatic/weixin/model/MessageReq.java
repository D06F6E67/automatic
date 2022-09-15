package com.lee.automatic.weixin.model;

import lombok.Data;

/**
 * 消息实体类接口
 *
 * @author Lee
 */
@Data
public class MessageReq {

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

    public MessageReq() {
        this.touser = "@all";
        this.msgtype = "textcard";
    }
}