package com.lee.automatic;

import com.lee.automatic.juejin.JueJinBugFixService;
import com.lee.automatic.juejin.JueJinChickInService;
import com.lee.automatic.juejin.game.JueJinGameService;
import com.lee.automatic.weixin.WeiXinSendMessage;
import com.lee.automatic.weixin.model.TextCardMessageReq;
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
    private JueJinChickInService junJinService;
    @Resource
    private JueJinBugFixService jueJinBugFixService;
    @Resource
    private JueJinGameService jueJinGameService;
    @Resource
    private YouDaoService youDaoService;
    @Resource
    private WeiXinSendMessage weiXinSendMessage;

    @Scheduled(cron = "0 0 11 ? * SAT,SUN")
    @Scheduled(cron = "0 30 8 ? * MON-FRI")
    public void job() {

        weiXinSendMessage.sendMessage(
                new TextCardMessageReq(
                        junJinService.job() + jueJinBugFixService.job()
                                + jueJinGameService.job()
                )
        );
    }
}
