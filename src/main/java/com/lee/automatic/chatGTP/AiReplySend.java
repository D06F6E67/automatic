package com.lee.automatic.chatGTP;

import com.lee.automatic.dingtalk.service.DingTalkSendMessageService;
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
    private WeiXinSendMessageService weiXinSendMessage;
    @Resource
    private DingTalkSendMessageService dingTalkSendMessage;

    /**
     * ai交互，主动发送ai回复的消息到微信
     *
     * @param content      询问内容
     * @param fromUserName 询问人
     */
    @Async
    public void WXSend(String content, String fromUserName) {

        String answer = aiService.ask(content);

        weiXinSendMessage.sendMessage(
                new TextMessageReq(
                        fromUserName,
                        RobotEnum.OPENAI,
                        answer));
    }

    /**
     * ai交互，主动发送ai回复的消息到钉钉
     *
     * @param content 询问内容
     * @param oto     单聊，群聊
     * @param id      单聊：询问人id 群聊：群id
     */
    @Async
    public void dingTalkSend(String content, boolean oto, String id) {

        String answer = aiService.ask(content);

        if (oto) {
            dingTalkSendMessage.oToMessages(id, answer);
        } else {
            dingTalkSendMessage.groupMessages(id, answer);
        }
    }
}
