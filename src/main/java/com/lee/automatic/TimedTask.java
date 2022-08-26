package com.lee.automatic;

import com.lee.automatic.juejin.JunJinService;
import com.lee.automatic.weixin.WinXinSendMessage;
import com.lee.automatic.youdao.YouDaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时任务
 *
 * @author Lee
 */
@Slf4j
@Component
public class TimedTask {

    @Resource
    private JunJinService junJinService;
    @Resource
    private YouDaoService youDaoService;
    @Resource
    private WinXinSendMessage winXinSendMessage;

    @Scheduled(cron = "0 0 11 ? * SAT,SUN")
    @Scheduled(cron = "0 30 8 ? * MON-FRI")
    public void job() {

        winXinSendMessage.sendMessage(String.format("%s\n%s",
                junJinService.job(),
                youDaoService.job()));
    }
}
