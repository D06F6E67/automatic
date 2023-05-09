package com.lee.automatic.youdao;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 有道笔记 配置信息
 *
 * @author Lee
 */
@Data
@Configuration
@ConfigurationProperties("youdao")
public class YouDaoConfig {

    /**
     * cookie
     */
    private String cookie;

    /**
     * 签到
     */
    public static final String CHECK_IN_URL = "https://note.youdao.com/yws/mapi/user?method=checkin";
}