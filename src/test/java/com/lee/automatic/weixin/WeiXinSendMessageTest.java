package com.lee.automatic.weixin;

import com.lee.automatic.weixin.model.TextCardMessageReq;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * 掘金 收集bug 测试
 *
 * @author Lee
 */
@SpringBootTest
@ActiveProfiles("local")
public class WeiXinSendMessageTest {

    @Resource
    private WeiXinSendMessage weiXinSendMessage;

    @Test
    public void getBug() {

        weiXinSendMessage.sendMessage(new TextCardMessageReq("掘金\n  签到结果：签到成功\n  抽奖结果：bug\n  矿石数量：12345\n  收集BUG ：5个\n  BUG数量 ：12345个\n"));

    }

}
