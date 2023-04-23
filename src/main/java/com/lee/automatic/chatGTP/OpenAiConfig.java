package com.lee.automatic.chatGTP;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * chatGPT openAI配置
 *
 * @author Lee
 */
@Data
@Configuration
@ConfigurationProperties("chatgpt.openai")
public class OpenAiConfig {

    /**
     * 授权令牌
     */
    private String authorization;
    /**
     * megumi页面会话ID
     */
    private String megumiParentMessageId;

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
     * 获取chatGPT回应
     */
    public static final String URL = "https://api.openai.com/v1/completions";
    /**
     * 使用 <a href="https://megumi.ml">web页面</a> url
     */
    public static final String MEGUMI_URL = "https://megumi.ml/chat-process";
}
