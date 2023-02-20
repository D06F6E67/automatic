package com.lee.automatic.weixin.model.send;

import com.alibaba.fastjson.annotation.JSONField;
import com.lee.automatic.weixin.RobotEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发送 消息实体类接口
 *
 * @author Lee
 */
@Data
@NoArgsConstructor
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

    /**
     * 使用那个机器人
     */
    @JSONField(serialize = false)
    private RobotEnum which;

    public MessageReq(String touser, String msgtype, RobotEnum which) {
        this.touser = touser;
        this.msgtype = msgtype;
        this.which = which;
    }
}