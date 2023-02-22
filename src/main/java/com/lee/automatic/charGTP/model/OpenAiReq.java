package com.lee.automatic.charGTP.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * charGPT 请求参数
 *
 * @author Lee
 */
@Data
public class OpenAiReq {

    /**
     * model
     */
    private String model;
    /**
     * prompt
     */
    private String prompt;
    /**
     * temperature
     */
    private Double temperature;
    /**
     * maxTokens
     */
    @JSONField(name = "max_tokens")
    private Integer maxTokens;

    public OpenAiReq(String prompt) {
        this.model = "text-davinci-003";
        this.prompt = prompt;
        this.temperature = 0.9;
        this.maxTokens = 2048;
    }
}
