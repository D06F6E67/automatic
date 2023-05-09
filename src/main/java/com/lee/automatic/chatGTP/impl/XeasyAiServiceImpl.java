package com.lee.automatic.chatGTP.impl;

import com.lee.automatic.chatGTP.OpenAiService;
import com.lee.automatic.chatGTP.model.XeasyReq;
import com.lee.automatic.common.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

/**
 * xeasy页面 ai问答
 *
 * @author Lee
 */
@Slf4j
@Service
public class XeasyAiServiceImpl implements OpenAiService, Ordered {

    /**
     * 使用 <a href="https://wet.xeasy.me/">web页面</a> url
     */
    public static final String URL = "https://wet.xeasy.me/api/generate";

    @Override
    public String ask(String prompt) {
        try {
            Response post = HttpUtils.post(URL, null, new XeasyReq(prompt));

            return post.body().string();
        } catch (Exception e) {
            log.error("xeasy页面 AI问答异常", e);
            return e.getMessage();
        }
    }

    @Override
    public int getOrder() {
        return 4;
    }
}