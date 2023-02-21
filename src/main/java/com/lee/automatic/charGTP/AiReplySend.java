package com.lee.automatic.charGTP;

import com.lee.automatic.weixin.RobotEnum;
import com.lee.automatic.weixin.model.send.TextMessageReq;
import com.lee.automatic.weixin.service.WeiXinSendMessageService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * openAi回复的消息发送
 *
 * @author Lee
 */
@Service
public class AiReplySend {

    @Resource
    private OpenAiService aiService;
    @Resource
    private WeiXinSendMessageService sendMessage;

    /**
     * ai交互，主动发送ai回复的消息
     *
     * @param content      询问内容
     * @param fromUserName 询问人
     */
    @Async
    public void WXSend(String content, String fromUserName) {

        String answer = aiService.ask(content);

        sendMessage.sendMessage(
                new TextMessageReq(
                        fromUserName,
                        RobotEnum.OPENAI,
                        answer));
    }
}
