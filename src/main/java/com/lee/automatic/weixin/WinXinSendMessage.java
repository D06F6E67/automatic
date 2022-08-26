package com.lee.automatic.weixin;

import com.alibaba.fastjson.JSONObject;
import com.lee.automatic.common.utils.HttpUtils;
import com.lee.automatic.weixin.model.TextMessageReq;
import com.lee.automatic.weixin.model.WeiXinResp;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 企业微信 发送消息
 * <pre><a href="https://developer.work.weixin.qq.com/resource/devtool">企业微信开发者调试平台</a></pre>
 * <pre><a href="https://work.weixin.qq.com/wework_admin/frame">企业微信管理平台</a></pre>
 *
 * @author Lee
 */
@Slf4j
@Service
public class WinXinSendMessage {

    @Resource
    private WeiXinConfig weiXinConfig;

    /**
     * 获取token
     *
     * @return token
     */
    private String getAccessToken() {
        try {
            Map<String, Object> params = new HashMap<>(2);
            params.put("corpid", weiXinConfig.getCorpId());
            params.put("corpsecret", weiXinConfig.getCorpsecret());

            Response response = HttpUtils.get(WeiXinConfig.ACCESS_TOKEN_URL, null, params);
            WeiXinResp weiXinResp = JSONObject.parseObject(response.body().string(), WeiXinResp.class);

            return weiXinResp.getAccessToken();
        } catch (Exception e) {
            log.error("企业微信获取token失败" + e);
        }
        return null;
    }

    /**
     * 微信发送文本消息
     *
     * @param message 文本内容
     * @return 返回信息
     */
    public String sendMessage(String message) {
        try {
            Map<String, Object> params = new HashMap<>(1);
            params.put("access_token", getAccessToken());

            Response response = HttpUtils.post(
                    HttpUtils.spliceUrl(WeiXinConfig.SEND_MESSAGE_URL, params), null,
                    new TextMessageReq(weiXinConfig.getAgentId(), message));

            WeiXinResp weiXinResp = JSONObject.parseObject(response.body().string(), WeiXinResp.class);
            return weiXinResp.getErrmsg();
        } catch (Exception e) {
            log.error("企业微信发送消息失败" + e);
        }
        return "企业微信发送消息失败";
    }
}
