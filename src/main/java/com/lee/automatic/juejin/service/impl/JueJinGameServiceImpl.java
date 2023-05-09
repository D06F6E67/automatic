package com.lee.automatic.juejin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lee.automatic.common.utils.HttpUtils;
import com.lee.automatic.juejin.config.GameConfig;
import com.lee.automatic.juejin.config.JueJinConfig;
import com.lee.automatic.juejin.model.JueJinResp;
import com.lee.automatic.juejin.model.UserInfoResp;
import com.lee.automatic.juejin.model.game.model.GameResp;
import com.lee.automatic.juejin.service.JueJinSeaGoldService;
import com.lee.automatic.juejin.service.JueJinService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.core.Ordered;

import javax.annotation.Resource;

/**
 * 掘金 游戏
 *
 * @author Lee
 */
@Slf4j
// @Service
public class JueJinGameServiceImpl implements JueJinService, Ordered {

    @Resource
    private JueJinConfig config;
    @Resource
    private GameConfig gameConfig;
    @Resource
    private JueJinSeaGoldService seaGoldService;

    /**
     * 获取uid，游戏必须参数
     */
    public void getUid() {
        try {
            Response response = HttpUtils.get(GameConfig.USER_INFO_URL, config.getHeaders(), null);
            JueJinResp<UserInfoResp> userInfoResp = JSON.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<UserInfoResp>>() {});

            gameConfig.setUid(userInfoResp.getData().getUserId());
            gameConfig.setUserName(userInfoResp.getData().getUserName());
        } catch (Exception e) {
            log.error("获取Uid异常", e);
        }
    }

    /**
     * 获取token，游戏必须
     */
    public void getToken() {
        try {
            Response response = HttpUtils.get(GameConfig.GET_TOKEN_URL, config.getHeaders(), null);
            GameResp<String> resp = JSON.parseObject(response.body().string(),
                    new TypeReference<GameResp<String>>() {});

            gameConfig.setToken(resp.getData());
        } catch (Exception e) {
            log.error("获取Token异常", e);
        }
    }

    /**
     * 海底掘金游戏
     *
     * @return 任务结果
     */
    @Override
    public String job() {
        getUid();
        getToken();

        return seaGoldService.autoSeaGold();
    }

    @Override
    public int getOrder() {
        return 4;
    }
}