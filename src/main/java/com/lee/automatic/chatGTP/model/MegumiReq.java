package com.lee.automatic.chatGTP.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Megumi 页面请求参数
 *
 * @author Lee
 */
@Data
@AllArgsConstructor
public class MegumiReq {
    /**
     * 问题
     */
    private String prompt;
    /**
     * 会话
     */
    private Options options;

    /**
     * 会话
     */
    @Data
    @AllArgsConstructor
    public static class Options {
        /**
         * 会话ID
         */
        private String parentMessageId;
    }
}