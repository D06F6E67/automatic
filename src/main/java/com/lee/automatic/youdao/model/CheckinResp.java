package com.lee.automatic.youdao.model;

import lombok.Data;

/**
 * 有道笔记签到 响应
 *
 * @author Lee
 */
@Data
public class CheckinResp {

    /**
     * 总获得
     */
    private Long total;
    /**
     * 时间戳
     */
    private Long time;
    /**
     * 签到状态 1/0 每日首次为1
     */
    private Integer success;
    /**
     * 本次获得
     */
    private Integer space;
}