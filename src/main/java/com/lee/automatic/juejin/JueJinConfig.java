package com.lee.automatic.juejin;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 掘金 配置信息
 *
 * @author Lee
 */
@Data
@Configuration
@ConfigurationProperties("juejin")
public class JueJinConfig {

    /**
     * cookie
     */
    private String cookie;

    /**
     * 获取今日状态
     */
    public static final String TODAY_STATUS_URL = "https://api.juejin.cn/growth_api/v1/get_today_status";
    /**
     * 签到
     */
    public static final String CHECK_IN_URL = "https://api.juejin.cn/growth_api/v1/check_in";
    /**
     * 查询免费抽奖次数
     */
    public static final String FREE_LOTTERY_URL = "https://api.juejin.cn/growth_api/v1/lottery_config/get";
    /**
     * 抽奖
     */
    public static final String LOTTERY_URL = "https://api.juejin.cn/growth_api/v1/lottery/draw";
    /**
     * 矿石数量
     */
    public static final String ORE_COUNT_URL = "https://api.juejin.cn/growth_api/v1/get_cur_point";

}
