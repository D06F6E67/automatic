package com.lee.automatic.chatGTP.impl;

import com.alibaba.fastjson.JSONObject;
import com.lee.automatic.chatGTP.OpenAiConfig;
import com.lee.automatic.chatGTP.OpenAiService;
import com.lee.automatic.chatGTP.model.MegumiReq;
import com.lee.automatic.common.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * megumi页面 ai问答
 *
 * @author Lee
 */
@Slf4j
@Service
public class MegumiAiServiceImpl implements OpenAiService, Ordered {

    /**
     * 使用 <a href="https://megumi.ml">web页面</a> url
     */
    public static final String URL = "https://megumi.ml/chat-process";

    @Resource
    private OpenAiConfig openAiConfig;

    @Override
    public String ask(String prompt) {
        try {
            Response post = HttpUtils.post(URL, null,
                    new MegumiReq(prompt, new MegumiReq.Options(openAiConfig.getMegumi())));

            String string = post.body().string();
            String substring = string.substring(string.lastIndexOf("\n"));

            return JSONObject.parseObject(substring).getString("text");

        } catch (Exception e) {
            log.error("megumi页面 AI问答异常", e);
            return e.getMessage();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
