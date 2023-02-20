package com.lee.automatic;

import com.lee.automatic.juejin.JueJinJob;
import com.lee.automatic.pudn.PudnChickInService;
import com.lee.automatic.weixin.RobotEnum;
import com.lee.automatic.weixin.service.WeiXinSendMessageService;
import com.lee.automatic.weixin.model.send.TextCardMessageReq;
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
    private JueJinJob jueJinJob;
    @Resource
    private PudnChickInService pudnChickInService;
    @Resource
    private YouDaoService youDaoService;
    @Resource
    private WeiXinSendMessageService WXSendMsgService;

    @Scheduled(cron = "0 0 11 ? * SAT,SUN")
    @Scheduled(cron = "0 30 8 ? * MON-FRI")
    public void job() {

        WXSendMsgService.sendMessage(
                new TextCardMessageReq(
                        RobotEnum.AUTOMATIC,
                        jueJinJob.job()
                                + pudnChickInService.job()
                )
        );
    }
}
