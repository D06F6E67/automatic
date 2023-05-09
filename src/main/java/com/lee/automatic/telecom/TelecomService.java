package com.lee.automatic.telecom;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lee.automatic.common.constant.Constant;
import com.lee.automatic.common.constant.HttpConstant;
import com.lee.automatic.common.utils.HttpUtils;
import com.lee.automatic.telecom.model.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 电信云宠物
 *
 * @author Lee
 */
@Slf4j
@Service
public class TelecomService {

    @Resource
    private TelecomConfig config;

    private int loginNumber = 0;

    /**
     * 获取token
     */
    private void getToken() {
        try {
            Response response = HttpUtils.post(TelecomConfig.GET_TOKEN_URL, null, null);

            config.setToken(response.header("csrf_token"));
            config.setSession(response.header("Set-Cookie"));
        } catch (Exception e) {
            log.error("电信云宠物 获取token异常", e);
        }
    }

    /**
     * 获取code
     *
     * @return code, 异常返回空
     */
    private String getCode() {
        try {
            Response response = HttpUtils.post(TelecomConfig.GET_CODE_URL, null, config.getGetCodeBody());
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());

            return jsonObject.getString("result_data");
        } catch (Exception e) {
            log.error("电信云宠物 获取code异常", e);
        }

        return "";
    }

    /**
     * 登陆
     */
    private boolean login() {
        try {
            getToken();

            String code = getCode();
            if (Objects.isNull(code) || code.length() < 1) {
                return false;
            }

            Response response = HttpUtils.post(TelecomConfig.LOGIN_URL, config.getHeaders(),
                    new LoginReq(code, config.getAid()));

            TelecomResp<String> resp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<TelecomResp<String>>() {});

            if (resp.getSuccess()) {
                return true;
            }

            if (loginNumber++ < Constant.LOGIN_MAX_RETRIES) {
                Thread.sleep(1000);
                login();
            }

            loginNumber = 0;
        } catch (Exception e) {
            log.error("电信云宠物 登陆异常", e);
        }
        return false;
    }

    /**
     * 签到
     *
     * @return 签到结果
     */
    public String chickIn() {
        try {
            Response post = HttpUtils.post(TelecomConfig.CHECK_IN_URL, config.getHeaders(),
                    new AidReq(config.getAid()));

            TelecomResp<String> resp = JSONObject.parseObject(post.body().string(),
                    new TypeReference<TelecomResp<String>>() {});

            if (Objects.isNull(resp) || resp.getCode() == HttpConstant.CODE_403) {
                if (login()) {
                    return chickIn();
                }
            }

            return resp.getMessage();
        } catch (Exception e) {
            log.error("电信云宠物 签到异常", e);
        }
        return "";
    }

    /**
     * 浏览页面任务
     */
    public String browsePageTask() {
        try {
            Response post = HttpUtils.post(TelecomConfig.DO_TASK_URL, config.getHeaders(),
                    new TaskReq(TelecomConfig.BROWSE_PAGE_TASK_ID, config.getAid()));

            TelecomResp<String> resp = JSONObject.parseObject(post.body().string(),
                    new TypeReference<TelecomResp<String>>() {});

            if (Objects.isNull(resp) || resp.getCode() == HttpConstant.CODE_403) {
                if (login()) {
                    return browsePageTask();
                }
            }

            return resp.getMessage();
        } catch (Exception e) {
            log.error("电信云宠物 浏览页面任务异常", e);
        }
        return "";
    }

    /**
     * 获取猫粮数量
     *
     * @return 猫粮数量 -1 异常
     */
    public Double getCatFoodAmount() {
        try {
            Response post = HttpUtils.post(TelecomConfig.GET_CAT_FOOD_URL, config.getHeaders(),
                    new AidReq(config.getAid()));

            TelecomResp<CatFoodResp> resp = JSONObject.parseObject(post.body().string(),
                    new TypeReference<TelecomResp<CatFoodResp>>() {});

            if (Objects.isNull(resp) || resp.getCode() == HttpConstant.CODE_403) {
                if (login()) {
                    return getCatFoodAmount();
                }
            }

            return resp.getResult().getAwardsNum().get(0).getValue();
        } catch (Exception e) {
            log.error("电信云宠物 获取猫粮数量异常", e);
        }
        return -1.0;
    }

    /**
     * 获取上次喂猫时间
     *
     * @return 上次喂猫时间 "" 异常
     */
    public String getLastTimeFeedCatDate() {
        try {
            Response post = HttpUtils.post(TelecomConfig.GET_FEED_CAT_DATE_URL, config.getHeaders(),
                    new TaskReq(TelecomConfig.FEED_CAT_TASK_ID, config.getAid()));

            TelecomResp<String> resp = JSONObject.parseObject(post.body().string(),
                    new TypeReference<TelecomResp<String>>() {});

            if (Objects.isNull(resp) || resp.getCode() == HttpConstant.CODE_403) {
                if (login()) {
                    return getLastTimeFeedCatDate();
                }
            }

            if (resp.getCode() == HttpConstant.CODE_9999) {
                return "没有喂猫";
            }

            FeedCatDateResp date = JSONObject.parseObject(resp.getResult(), FeedCatDateResp.class);
            return date.getTime();
        } catch (Exception e) {
            log.error("电信云宠物 获取上次喂猫时间异常", e);
        }
        return "";
    }

    /**
     * 喂猫
     *
     * @return 喂猫结果
     */
    public boolean feedCat() {
        try {
            Response post = HttpUtils.post(TelecomConfig.FEED_CAT_URL, config.getHeaders(),
                    new TaskReq(TelecomConfig.FEED_CAT_TASK_ID, config.getAid()));

            TelecomResp<String> resp = JSONObject.parseObject(post.body().string(),
                    new TypeReference<TelecomResp<String>>() {});

            if (Objects.isNull(resp) || resp.getCode() == HttpConstant.CODE_403) {
                if (login()) {
                    feedCat();
                }
            }

            return true;
        } catch (Exception e) {
            log.error("电信云宠物 喂猫异常", e);
        }
        return false;
    }

    /**
     * 电信云宠物
     *
     * @return 任务结果
     */
    public String job() {
        return String.format("电信\n  签到结果：%s\n  浏览任务：%s\n  猫粮数量：%s\n  喂猫时间：%s\n",
                chickIn(), browsePageTask(), getCatFoodAmount(), getLastTimeFeedCatDate());
    }
}