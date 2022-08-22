package com.lee.automatic.dingtalk;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.lee.automatic.common.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉机器人发送消息
 *
 * @author Lee
 */
@Slf4j
@Service
public class RobotSendMessage {

    @Resource
    private DingTalkConfig dingTalkConfig;

    /**
     * 发送消息
     *
     * @param content 内容
     */
    public void sendText(String content){
        try {
            Map<String, Object> params = encode();
            params.put("access_token", dingTalkConfig.getAccessToken());

            DingTalkClient client = new DefaultDingTalkClient(HttpUtils.spliceUrl(DingTalkConfig.ROBOT_SEND_URL, params));
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent(content);
            request.setText(text);

            client.execute(request);
        } catch (Exception e) {
            log.error("钉钉发送消息失败" + e);
        }

    }

    /**
     * 钉钉加签
     *
     * @return 签名数据
     */
    private Map<String, Object> encode() {
        try {
            Map<String, Object> result = new HashMap<>(3);

            Long timestamp = System.currentTimeMillis();
            result.put("timestamp", timestamp);

            String secret = dingTalkConfig.getSecret();
            String stringToSign = timestamp + "\n" + secret;

            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));

            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));

            BASE64Encoder encoder = new BASE64Encoder();
            String sign = URLEncoder.encode(encoder.encode(signData),"UTF-8");
            result.put("sign", sign);

            return result;
        } catch (Exception e) {
            log.error("钉钉加密失败" + e);
            return null;
        }
    }
}
