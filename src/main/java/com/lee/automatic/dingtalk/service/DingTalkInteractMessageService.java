package com.lee.automatic.dingtalk.service;

import com.lee.automatic.chatGTP.AiReplySend;
import com.lee.automatic.dingtalk.DingTalkConfig;
import com.lee.automatic.dingtalk.model.ReceiveMessageResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * 钉钉消息接收
 * <a href="https://open.dingtalk.com/document/isvapp/receive-message-3">接收消息</a>
 *
 * @author Lee
 */
@Slf4j
@Service
public class DingTalkInteractMessageService {

    @Resource
    private DingTalkConfig config;
    @Resource
    private AiReplySend aiReplySend;

    public void receiveMessage(ReceiveMessageResp receiveMessage, HttpServletRequest request) {

        // 消息发送的时间戳，单位是毫秒
        Long timestamp = Long.valueOf(request.getHeader("timestamp"));
        // 签名值
        String sign = request.getHeader("sign");

        try {
            String stringToSign = timestamp + "\n" + config.getAppSecret();
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(config.getAppSecret().getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));

            if (sign.equals(new String(Base64.encodeBase64(signData)))) {
                boolean oto = "1".equals(receiveMessage.getConversationType());

                String id = oto ? receiveMessage.getSenderStaffId() : receiveMessage.getConversationId();
                aiReplySend.dingTalkSend(
                        receiveMessage.getText().getContent(),
                        oto, id);
            }
        } catch (Exception e) {
            log.error("钉钉消息接收异常", e);
        }
    }
}