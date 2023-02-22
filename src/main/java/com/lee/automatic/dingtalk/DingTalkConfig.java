package com.lee.automatic.dingtalk;

import com.aliyun.teaopenapi.models.Config;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 钉钉 配置信息
 *
 * @author Lee
 */
@Data
@Slf4j
@Configuration
@ConfigurationProperties("dingtalk")
public class DingTalkConfig {

    /**
     * 企业内部应用的AppKey
     */
    private String appKey;
    /**
     * 企业内部应用的AppSecret
     */
    private String appSecret;


    /**
     * 授权 Client
     */
    private com.aliyun.dingtalkoauth2_1_0.Client authClient;
    /**
     * 机器人 Client
     */
    private com.aliyun.dingtalkrobot_1_0.Client robotClient;

    public com.aliyun.dingtalkoauth2_1_0.Client getAuthClient() {

        try {
            if (authClient == null) {
                Config config = new Config();
                config.protocol = "https";
                config.regionId = "central";
                authClient = new com.aliyun.dingtalkoauth2_1_0.Client(config);
            }
        } catch (Exception e) {
            log.error("钉钉初始化授权Client配置信息异常", e);
        }

        return authClient;
    }

    public com.aliyun.dingtalkrobot_1_0.Client getRobotClient() {

        try {
            if (robotClient == null) {
                Config config = new Config();
                config.protocol = "https";
                config.regionId = "central";
                robotClient = new com.aliyun.dingtalkrobot_1_0.Client(config);
            }
        } catch (Exception e) {
            log.error("钉钉初始化机器人Client配置信息异常", e);
        }

        return robotClient;
    }
}
