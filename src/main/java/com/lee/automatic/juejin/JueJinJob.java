package com.lee.automatic.juejin;

import com.lee.automatic.weixin.WinXinSendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 掘金 签到抽奖定时任务
 * <pre><a href="https://blog.csdn.net/qq_35092597/article/details/125157948">参考</a></pre>
 *
 * @author Lee
 */
@Slf4j
@Configuration
public class JueJinJob {

    @Resource
    private JunJinService junJinService;
    @Resource
    private JueJinConfig jueJinConfig;
    @Resource
    private WinXinSendMessage winXinSendMessage;

    @Scheduled(cron = "0 0 13 ? * 1,7")
    @Scheduled(cron = "0 50 8 ? * 2,3,4,5,6")
    public void job() {
        Map<String, String> headers = new HashMap<>(1);
        headers.put("cookie", jueJinConfig.getCookie());

        log.info(jueJinConfig.getCookie());

        winXinSendMessage.sendMessage(
                String.format("掘金签到结果：%s\n掘金抽奖结果：%s",
                        junJinService.checkIn(headers),
                        junJinService.lottery(headers)));
    }
}