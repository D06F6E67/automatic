package com.lee.automatic.chatGTP;

import com.lee.automatic.dingtalk.service.DingTalkSendMessageService;
import com.lee.automatic.weixin.RobotEnum;
import com.lee.automatic.weixin.model.send.TextMessageReq;
import com.lee.automatic.weixin.service.WeiXinSendMessageService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

/**
 * openAi回复的消息发送
 *
 * @author Lee
 */
@Service
public class AiReplySend {

    @Resource
    private WeiXinSendMessageService weiXinSendMessage;
    @Resource
    private DingTalkSendMessageService dingTalkSendMessage;

    private List<OpenAiService> aiService;
    private int currentChat;

    public AiReplySend(List<OpenAiService> aiService) {
        this.aiService = aiService;
        this.currentChat = aiService.size() - 1;
    }

    /**
     * ai交互，主动发送ai回复的消息到微信
     *
     * @param content      询问内容
     * @param fromUserName 询问人
     */
    @Async
    public void WXSend(String content, String fromUserName) {

        String answer = ask(content);

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

        String answer = ask(content);

        if (oto) {
            dingTalkSendMessage.oToMessages(id, answer);
        } else {
            dingTalkSendMessage.groupMessages(id, answer);
        }
    }

    private String ask(String content) {

        if (Pattern.matches("^(\\d+)#.*", content)) {
            String[] split = content.split("#");
            currentChat = Integer.parseInt(split[0]);
            content = split[1];
        }

        if (currentChat < 0 || currentChat >= aiService.size()) {
            currentChat = aiService.size() - 1;
            return String.format("请选择0～%s的会话", aiService.size() - 1);
        }

        return aiService.get(currentChat).ask(content);
    }
}
