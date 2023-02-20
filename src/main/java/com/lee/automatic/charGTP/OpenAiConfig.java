package com.lee.automatic.charGTP;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * charGPT openAI配置
 *
 * @author Lee
 */
@Data
@Configuration
@ConfigurationProperties("chargpt.openai")
public class OpenAiConfig {

    /**
     * 授权令牌
     */
    private String authorization;

    /**
     * 请求头信息
     */
    private Map<String, String> headers;

    public Map<String, String> getHeaders() {
        if (Objects.isNull(headers)) {
            headers = new HashMap<>(1);
            headers.put("authorization", "Bearer " + authorization);
        }

        return headers;
    }


    /**
     * 用户信息
     */
    public static final String URL = "https://api.openai.com/v1/completions";
}
