package com.lee.automatic.pudn;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * pudn 配置信息
 *
 * @author Lee
 */
@Data
@Configuration
@ConfigurationProperties("pudn")
public class PudnConfig {

    /**
     * 授权
     */
    private String authorization;

    /**
     * 请求头信息
     */
    private Map<String, String> headers;

    public Map<String, String> getHeaders() {

        if (Objects.isNull(headers)) {
            headers = new HashMap<>(1);
            headers.put("authorization", authorization);
        }

        return headers;
    }

    /**
     * 签到
     */
    public static String CHICK_IN_URL = "https://api.pudn.com/portal/signIn/signIn";
    /**
     * 获取金币状态
     */
    public static String GOLD_INFO_URL = "https://api.pudn.com/portal/gold/info";

}
