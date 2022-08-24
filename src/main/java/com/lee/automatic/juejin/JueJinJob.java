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

    @Scheduled(cron = "0 0 11 ? * SAT,SUN")
    @Scheduled(cron = "0 30 8 ? * MON-FRI")
    public void job() {
        Map<String, String> headers = new HashMap<>(1);
        headers.put("cookie", jueJinConfig.getCookie());

        winXinSendMessage.sendMessage(
                String.format("掘金签到结果：%s\n掘金抽奖结果：%s\n掘金矿石数量：%s",
                        junJinService.checkIn(headers),
                        junJinService.lottery(headers),
                        junJinService.getOreCount(headers)));
    }
}
