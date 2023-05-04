package com.lee.automatic.chatGTP;

/**
 * chatGPT openAI service
 *
 * @author Lee
 */
public interface OpenAiService {

    /**
     * ai问答
     *
     * @param prompt 问
     * @return 答  空白出现异常
     */
    String ask(String prompt);
}
