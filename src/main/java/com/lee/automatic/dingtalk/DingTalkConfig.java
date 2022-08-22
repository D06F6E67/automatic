package com.lee.automatic.dingtalk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 钉钉 配置信息
 *
 * @author Lee
 */
@Data
@Configuration
@ConfigurationProperties("dingtalk")
public class DingTalkConfig {

    /**
     * token
     */
    public String accessToken;
    /**
     * 密钥
     */
    public String secret;


    /**
     * 机器人发送消息
     */
    public static final String ROBOT_SEND_URL = "https://oapi.dingtalk.com/robot/send";

}
