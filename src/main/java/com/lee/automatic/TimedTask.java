package com.lee.automatic;

import com.lee.automatic.juejin.JueJinJob;
import com.lee.automatic.telecom.TelecomService;
import com.lee.automatic.weixin.RobotEnum;
import com.lee.automatic.weixin.model.send.TextCardMessageReq;
import com.lee.automatic.weixin.service.WeiXinSendMessageService;
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
    private JueJinJob jueJinJob;
    @Resource
    private WeiXinSendMessageService wxSendMsgService;
    @Resource
    private TelecomService telecomService;

    @Scheduled(cron = "0 0 11 ? * SAT,SUN")
    @Scheduled(cron = "0 30 8 ? * MON-FRI")
    public void job() {

        wxSendMsgService.sendMessage(
                new TextCardMessageReq(
                        RobotEnum.AUTOMATIC,
                        jueJinJob.job()
                )
        );
    }

    @Scheduled(cron = "0 0,3 * * * *")
    public void feedCat() {
        telecomService.feedCat();
    }
}