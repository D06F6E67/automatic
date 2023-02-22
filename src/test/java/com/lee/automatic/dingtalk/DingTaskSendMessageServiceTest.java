package com.lee.automatic.dingtalk;

import com.lee.automatic.dingtalk.service.DingTalkSendMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * 钉钉机器人发送消息
 *
 * @author Lee
 */
@SpringBootTest
@ActiveProfiles("local")
public class DingTaskSendMessageServiceTest {

    @Resource
    private DingTalkSendMessageService service;

    @Test
    public void getToken() {
        String token = service.getToken();

        Assert.hasLength(token, "token获取异常");

        System.out.println(token);
    }

    @Test
    public void oToMessages() {
        service.oToMessages("273664192526169059", "hello");
    }

    @Test
    public void groupMessages() {
        service.groupMessages("cidP7b4hZreq+MQZo/uRu/opg==", "hello");
    }

}
