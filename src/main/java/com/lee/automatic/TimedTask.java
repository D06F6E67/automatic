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

    /**
     * 每日签到任务
     * 周六周日 11点运行
     * 周一～周五8点半运行
     */
    @Scheduled(cron = "0 0 11 ? * SAT,SUN")
    @Scheduled(cron = "0 30 8 ? * MON-FRI")
    public void job() {

        wxSendMsgService.sendMessage(
                new TextCardMessageReq(
                        RobotEnum.AUTOMATIC,
                        jueJinJob.job()
                                // + telecomService.job()
                )
        );
    }

    // /**
    //  * 电信喂猫任务
    //  * 5分钟的0秒和30秒运行
    //  */
    // @Scheduled(cron = "0,30 0/5 * * * *")
    // public void feedCat() {
    //     if (telecomService.feedCat() ) {
    //         telecomService.feedCat();
    //     }
    // }
}