package com.lee.automatic.chatGTP.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lee.automatic.chatGTP.OpenAiConfig;
import com.lee.automatic.chatGTP.OpenAiService;
import com.lee.automatic.chatGTP.model.OpenAiReq;
import com.lee.automatic.common.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * chatGPT 官方
 *
 * @author Lee
 */
@Slf4j
@Service
public class ChatGPTAiServiceImpl implements OpenAiService, Ordered {

    /**
     * 获取chatGPT回应
     */
    public static final String URL = "https://api.openai.com/v1/completions";

    @Resource
    private OpenAiConfig openAiConfig;

    /**
     * ai问答
     *
     * @param prompt 问
     * @return 答  空白出现异常
     */
    @Override
    public String ask(String prompt) {
        try {
            Response post = HttpUtils.post(URL, openAiConfig.getHeaders(), new OpenAiReq(prompt));

            JSONObject jsonObject = JSON.parseObject(post.body().string());

            JSONObject error = jsonObject.getJSONObject("error");
            if (Objects.nonNull(error)) {
                return error.getString("message");
            }

            return jsonObject.getJSONArray("choices").getJSONObject(0).getString("text");
        } catch (Exception e) {
            log.error("ChatGPT AI问答异常", e);
            return e.getMessage();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
