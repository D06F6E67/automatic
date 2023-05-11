package com.lee.automatic.telecom;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 电信云宠物 配置
 *
 * @author Lee
 */
@Data
@Configuration
@ConfigurationProperties("telecom")
public class TelecomConfig {

    /**
     * 小程序令牌
     */
    private String aid;
    /**
     * 获取Code body
     */
    private String getCodeBody;

    /**
     * 请求头信息
     */
    private Map<String, String> headers;

    public Map<String, String> getHeaders() {
        if (Objects.isNull(headers)) {
            headers = new HashMap<>(4);
            headers.put("X-Requested-With", "XMLHttpRequest");
            headers.put("Referer", "https://hdmf.k189.cn/acquireHd/");
        }

        return headers;
    }

    public void setToken(String token) {
        getHeaders();

        headers.put("csrf_token", token);
    }

    public void setSession(String session) {
        getHeaders();

        headers.put("Cookie", session);
    }

    /**
     * 获取Token
     */
    public static String GET_TOKEN_URL = "https://hdmf.k189.cn/actServ/userJoin/getToken";
    /**
     * 获取Code
     */
    public static String GET_CODE_URL = "https://api.k189.cn/app1/api";
    /**
     * 登陆
     */
    public static String LOGIN_URL = "https://hdmf.k189.cn/actServ/userJoin/getUserInfoByCode";
    /**
     * 查询签到
     */
    public static String QUERY_CHECK_IN_URL = "https://hdmf.k189.cn/actServ/userActivity/querySignInData";
    /**
     * 签到
     */
    public static String CHECK_IN_URL = "https://hdmf.k189.cn/actServ/userActivity/signIn";
    /**
     * 做任务
     */
    public static String DO_TASK_URL = "https://hdmf.k189.cn/actServ/task/doTask";
    /**
     * 获取猫粮数量
     */
    public static String GET_CAT_FOOD_URL = "https://hdmf.k189.cn/actServ/activityData/findPrizes";
    /**
     * 获取上次喂猫时间
     */
    public static String GET_FEED_CAT_DATE_URL = "https://hdmf.k189.cn/actServ/task/rTaskTime";
    /**
     * 喂猫
     */
    public static String FEED_CAT_URL = "https://hdmf.k189.cn/actServ/task/bgTimeTask";

    /**
     * 浏览页面任务ID
     */
    public static String BROWSE_PAGE_TASK_ID = "1657702347690";
    /**
     * 喂猫任务ID
     */
    public static String FEED_CAT_TASK_ID = "1657682963913";
}