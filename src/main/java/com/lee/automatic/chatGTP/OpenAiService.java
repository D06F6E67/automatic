package com.lee.automatic.chatGTP;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lee.automatic.chatGTP.model.MegumiReq;
import com.lee.automatic.chatGTP.model.OpenAiReq;
import com.lee.automatic.common.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

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

    /**
     * megumi页面 ai问答
     *
     * @param prompt 问
     * @return 答  空白出现异常
     */
    public String megumiAsk(String prompt) {
        try {
            Response post = HttpUtils.post(OpenAiConfig.MEGUMI_URL,
                    null,
                    new MegumiReq(prompt, new MegumiReq.Options(openAiConfig.getMegumiParentMessageId())));

            String string = post.body().string();
            String substring = string.substring(string.lastIndexOf("\n"));

            return JSONObject.parseObject(substring).getString("text");

        } catch (Exception e) {
            log.error("megumi页面 AI问答异常", e);
            return e.getMessage();
        }
    }
}
