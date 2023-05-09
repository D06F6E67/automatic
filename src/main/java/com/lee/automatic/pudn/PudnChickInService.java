package com.lee.automatic.pudn;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lee.automatic.common.utils.HttpUtils;
import com.lee.automatic.pudn.model.GoldInfoResp;
import com.lee.automatic.pudn.model.LoginReq;
import com.lee.automatic.pudn.model.LoginResp;
import com.lee.automatic.pudn.model.PudnResp;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * pudn 签到获取金币
 * <p>
 * <a href="https://www.pudn.com/shop">pudn商店</a>
 *
 * @author Lee
 */
@Slf4j
@Service
public class PudnChickInService {

    @Resource
    private PudnConfig pudnConfig;

    public void login() {
        try {
            Response response = HttpUtils.post(PudnConfig.LOGIN_URL, null,
                    new LoginReq(pudnConfig.getUsername(), pudnConfig.getPassword()));

            PudnResp<LoginResp> pudnResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<PudnResp<LoginResp>>() {});

            pudnConfig.setHeaders(pudnResp.getData().getToken());
        } catch (Exception e) {
            log.error("pudn登陆异常", e);
        }
    }

    /**
     * pudn签到
     *
     * @return 签到结果
     */
    public String chickIn() {
        try {

            login();

            Response response = HttpUtils.get(PudnConfig.CHICK_IN_URL, pudnConfig.getHeaders(), null);

            PudnResp<String> pudnResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<PudnResp<String>>() {});

            return pudnResp.getData();
        } catch (Exception e) {
            log.error("pudn签到异常", e);
            return "签到失败";
        }
    }

    /**
     * 金币信息
     *
     * @return 金币数量,过期信息
     *   <pre>-1：异常</pre>
     */
    public String goldInfo() {
        try {
            Response response = HttpUtils.get(PudnConfig.GOLD_INFO_URL, pudnConfig.getHeaders(), null);

            PudnResp<GoldInfoResp> pudnResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<PudnResp<GoldInfoResp>>() {});

            if (pudnResp.getCode() == 200) {
                GoldInfoResp goldInfo = pudnResp.getData();

                if (StringUtils.hasLength(goldInfo.getGoldExpirationMsg())) {
                    return String.format("%s, %s", goldInfo.getGoldSum(), goldInfo.getGoldExpirationMsg());
                }

                return goldInfo.getGoldSum().toString();
            }

        } catch (Exception e) {
            log.error("pudn获取金币信息异常", e);
        }
        return "-1";
    }

    /**
     * pudn签到、获取金币数量
     *
     * @return 任务结果
     */
    public String job() {
        return String.format("pudn\n  签到结果：%s\n  金币数量：%s\n",
                chickIn(), goldInfo());
    }
}