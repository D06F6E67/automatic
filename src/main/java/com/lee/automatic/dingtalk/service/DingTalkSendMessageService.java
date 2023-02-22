package com.lee.automatic.dingtalk.service;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenResponse;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOHeaders;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.aliyun.dingtalkrobot_1_0.models.OrgGroupSendHeaders;
import com.aliyun.dingtalkrobot_1_0.models.OrgGroupSendRequest;
import com.aliyun.teautil.models.RuntimeOptions;
import com.lee.automatic.dingtalk.DingTalkConfig;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉机器人发送消息
 *
 * @author Lee
 */
@Slf4j
@Service
public class DingTalkSendMessageService {

    @Resource
    private DingTalkConfig config;

    /**
     * 获取Token
     * <a href="https://open-dev.dingtalk.com/apiExplorer?spm=ding_open_doc.document.0.0.6077287frfFLpX#/?devType=org&api=dingtalk.oapi.gettoken">测试工具</a>
     * <a href="https://open.dingtalk.com/document/orgapp/obtain-the-access_token-of-an-internal-app">文档</a>
     *
     * @return token 空字符串表示异常
     */
    public String getToken() {
        GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest()
                .setAppKey(config.getAppKey())
                .setAppSecret(config.getAppSecret());
        try {
            GetAccessTokenResponse accessToken = config.getAuthClient().getAccessToken(getAccessTokenRequest);
            return accessToken.getBody().accessToken;
        } catch (Exception e) {
            log.error("钉钉获取Token异常", e);
        }

        return StringUtil.EMPTY_STRING;
    }

    /**
     * 发送消息给个人
     * <a href="https://open-dev.dingtalk.com/apiExplorer?spm=ding_open_doc.document.0.0.6077287frfFLpX#/?devType=org&api=robot_1.0%23BatchSendOTO">测试工具</a>
     * <a href="https://open.dingtalk.com/document/isvapp/send-single-chat-messages-in-bulk">文档</a>
     *
     * @param userId
     * @param message
     */
    public void oToMessages(String userId, String message) {

        Map<String, String> msgParam = new HashMap<>(2);
        msgParam.put("title", message);
        msgParam.put("text", message);

        BatchSendOTOHeaders batchSendOTOHeaders = new BatchSendOTOHeaders();
        batchSendOTOHeaders.xAcsDingtalkAccessToken = getToken();
        BatchSendOTORequest batchSendOTORequest = new BatchSendOTORequest()
                .setRobotCode(config.getAppKey())
                .setUserIds(java.util.Arrays.asList(
                        userId
                ))
                .setMsgKey("sampleMarkdown")
                .setMsgParam(JSON.toJSONString(msgParam));
        try {
            config.getRobotClient().batchSendOTOWithOptions(batchSendOTORequest, batchSendOTOHeaders, new RuntimeOptions());
        } catch (Exception e) {
            log.error("钉钉发送消息给个人异常", e);
        }

    }

    /**
     * 发送消息到群聊
     * <a href="https://open-dev.dingtalk.com/apiExplorer?spm=ding_open_doc.document.0.0.6077287frfFLpX#/?devType=org&api=robot_1.0%23OrgGroupSend">测试工具</a>
     * <a href="https://open.dingtalk.com/document/isvapp/bots-send-group-chat-messages">文档</a>
     *
     * @param groupId 群id
     * @param message 消息
     */
    public void groupMessages(String groupId, String message) {

        Map<String, String> msgParam = new HashMap<>(2);
        msgParam.put("title", message);
        msgParam.put("text", message);

        OrgGroupSendHeaders orgGroupSendHeaders = new OrgGroupSendHeaders();
        orgGroupSendHeaders.xAcsDingtalkAccessToken = getToken();
        OrgGroupSendRequest orgGroupSendRequest = new OrgGroupSendRequest()
                .setMsgParam(JSON.toJSONString(msgParam))
                .setMsgKey("sampleMarkdown")
                .setRobotCode(config.getAppKey())
                .setOpenConversationId(groupId);
        try {
            config.getRobotClient().orgGroupSendWithOptions(orgGroupSendRequest, orgGroupSendHeaders, new RuntimeOptions());
        } catch (Exception e) {
            log.error("钉钉发送消息到群聊异常", e);
        }
    }
}
