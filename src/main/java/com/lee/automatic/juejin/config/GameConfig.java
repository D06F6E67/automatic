package com.lee.automatic.juejin.config;

import com.lee.automatic.common.utils.DateUtils;
import lombok.Data;
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
public class GameConfig {

    /**
     * uid，部分接口必须
     */
    private String uid;
    /**
     * token，部分接口必须
     */
    private String token;
    /**
     * 用户名，登陆使用
     */
    private String userName;
    /**
     * 海底掘金数据加密密钥
     */
    public final String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
            "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgMHsoxVB3554q3sDA\n" +
            "MwxRpPsMTdKXQy2jcsSiOcSRGIChRANCAAQSRWIkNTyVglR6dLojE+W8Um1QIM0X\n" +
            "Rz8X9xfTsoLB4LhD+c4qyhSdgmxvg7yUyo1dojWPXo6ZeT0KRxBBdvN+\n" +
            "-----END PRIVATE KEY-----";

    /**
     * 请求头信息
     */
    private Map<String, String> headers;

    public Map<String, String> getHeaders() {
        if (Objects.isNull(headers)) {
            headers = new HashMap<>(1);
            headers.put("authorization", "Bearer " + token);
        }

        return headers;
    }

    /**
     * 请求url参数
     */
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (Objects.isNull(params)) {
            params = new HashMap<>(2);
            params.put("uid", uid);
        }
        params.put("time", DateUtils.getTimeMillis());

        return params;
    }

    /**
     * 单局最小矿石数量
     */
    public static final int ROUND_MIN_NUMBER = 50;
    /**
     * 单个地图运行最大次数
     */
    public static final int MAP_RUN_MAX_NUMBER = 5;
    /**
     * 游戏运行最大次数
     */
    public static final int RUN_MAX_NUMBER = 300;
    /**
     * 列的数量
     */
    public static final int COL_NUMBER = 6;
    /**
     * 障碍在地图中的值
     */
    public static final int OBSTACLES = 6;

    /**
     * 用户信息
     */
    public static final String USER_INFO_URL = "https://api.juejin.cn/user_api/v1/user/get";
    /**
     * 获取token
     */
    public static final String GET_TOKEN_URL = "https://juejin.cn/get/token";
    /**
     * 海底掘金登陆
     */
    public static final String SEA_LOGIN_URL = "https://juejin-game.bytedance.com/game/sea-gold/user/login";
    /**
     * 海底掘金主页信息
     */
    public static final String SEA_HOME_INFO_URL = "https://juejin-game.bytedance.com/game/sea-gold/home/info";
    /**
     * 海底掘金开始
     */
    public static final String SEA_START_URL = "https://juejin-game.bytedance.com/game/sea-gold/game/start";
    /**
     * 海底掘金移动
     */
    public static final String SEA_MOVE_URL = "https://juejin-game.bytedance.com/game/sea-gold/game/command";
    /**
     * 海底掘金结束
     */
    public static final String SEA_END_URL = "https://juejin-game.bytedance.com/game/sea-gold/game/over";
    /**
     * 海底掘金刷新
     */
    public static final String SEA_FRESH_URL = "https://juejin-game.bytedance.com/game/sea-gold/game/fresh_map";
}