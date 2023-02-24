package com.lee.automatic.chatGTP;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lee.automatic.chatGTP.model.OpenAiReq;
import com.lee.automatic.common.utils.HttpUtils;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * chatGPT openAI service
 *
 * @author Lee
 */
@Slf4j
@Service
public class OpenAiService {

    @Resource
    private OpenAiConfig openAiConfig;

    /**
     * ai问答
     *
     * @param prompt 问
     * @return 答  空白出现异常
     */
    public String ask(String prompt) {
        try {
            Response post = HttpUtils.post(OpenAiConfig.URL, openAiConfig.getHeaders(), new OpenAiReq(prompt));

            JSONObject jsonObject = JSON.parseObject(post.body().string());

            return jsonObject.getJSONArray("choices").getJSONObject(0).getString("text");

        } catch (IOException e) {
            log.error("ChatGPT AI问答异常", e);
        }

        return StringUtil.EMPTY_STRING;
    }
}
