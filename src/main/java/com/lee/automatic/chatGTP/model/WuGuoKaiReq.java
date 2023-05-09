package com.lee.automatic.chatGTP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * wuguokai 页面请求参数
 *
 * @author Lee
 */
@Data
@AllArgsConstructor
public class WuGuoKaiReq {

    /**
     * 问题
     */
    private String prompt;
    /**
     * 参数
     */
    private Options options;
    /**
     * userId
     */
    private String userId;
    /**
     * 启用上下文
     */
    private Boolean usingContext;

    /**
     * Options
     */
    @NoArgsConstructor
    @Data
    public static class Options {
    }
}