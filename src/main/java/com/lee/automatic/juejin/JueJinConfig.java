package com.lee.automatic.juejin;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
     * 请求头信息
     */
    private Map<String, String> headers;


    public Map<String, String> getHeaders() {

        if (Objects.isNull(headers)) {
            headers = new HashMap<>(1);
            headers.put("cookie", cookie);
        }

        return headers;
    }

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
     * 沾运气
     */
    public static final String DIP_LUCKY_URL = "https://api.juejin.cn/growth_api/v1/lottery_lucky/dip_lucky";
    /**
     * 矿石数量
     */
    public static final String ORE_COUNT_URL = "https://api.juejin.cn/growth_api/v1/get_cur_point";
    /**
     * 获取bug
     */
    public static final String GET_BUGFIX_URL = "https://api.juejin.cn/user_api/v1/bugfix/not_collect";
    /**
     * 收集bug
     */
    public static final String COLLECT_BUGFIX_URL = "https://api.juejin.cn/user_api/v1/bugfix/collect";
    /**
     * 获取活动ID
     */
    public static final String GET_COMPETITION_URL = "https://api.juejin.cn/user_api/v1/bugfix/competition";
    /**
     * bug数量
     */
    public static final String BUG_COUNT_URL = "https://api.juejin.cn/user_api/v1/bugfix/user";

}
