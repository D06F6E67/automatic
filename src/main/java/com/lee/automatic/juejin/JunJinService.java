package com.lee.automatic.juejin;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lee.automatic.common.utils.HttpUtils;
import com.lee.automatic.juejin.model.FreeLotteryResp;
import com.lee.automatic.juejin.model.JueJinResp;
import com.lee.automatic.juejin.model.LotteryResp;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 掘金 业务处理
 * <pre><a href="https://blog.csdn.net/qq_35092597/article/details/125157948">参考</a></pre>
 *
 * @author Lee
 */
@Slf4j
@Service
public class JunJinService {

    @Resource
    private JueJinConfig jueJinConfig;

    /**
     * 签到
     *
     * @param headers 请求头
     * @return 签到结果
     */
    public String checkIn(Map<String, String> headers) {
        try {
            Response response = HttpUtils.get(JueJinConfig.TODAY_STATUS_URL, headers, null);
            JueJinResp<Boolean> jueJinResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<Boolean>>(){});

            if (jueJinResp.getData()) {
                return "今日已签到";
            }

            HttpUtils.post(JueJinConfig.CHECK_IN_URL, headers, null);

            return "签到成功";
        } catch (Exception e) {
            log.error("掘金签到失败", e);
            return "签到失败";
        }
    }

    /**
     * 抽奖
     *
     * @param headers 请求头
     * @return 抽奖结果
     */
    public String lottery(Map<String, String> headers) {
        try {
            Response response = HttpUtils.get(JueJinConfig.FREE_LOTTERY_URL, headers, null);
            JueJinResp<FreeLotteryResp> jueJinResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<FreeLotteryResp>>(){});

            if (jueJinResp.getErrNo() != 0) {
                return "获取免费抽奖次数失败";
            }
            if (jueJinResp.getData().getFreeCount() <= 0) {
                return "没有免费抽奖次数";
            }

            Response post = HttpUtils.post(JueJinConfig.LOTTERY_URL, headers, null);
            JueJinResp<LotteryResp> lotteryResp = JSONObject.parseObject(post.body().string(),
                    new TypeReference<JueJinResp<LotteryResp>>() {});

            return lotteryResp.getData().getLotteryName();
        } catch (Exception e) {
            log.error("掘金抽奖失败", e);
            return "抽奖失败";
        }
    }

    /**
     * 矿石数量
     *
     * @param headers 请求头
     * @return 矿石数量
     *          <pre>-1：异常</pre>
     */
    public Integer getOreCount(Map<String, String> headers) {
        try {

            Response response = HttpUtils.get(JueJinConfig.ORE_COUNT_URL, headers, null);
            JueJinResp<Integer> jueJinResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<Integer>>() {});

            if (jueJinResp.getErrNo() == 0) {
                return jueJinResp.getData();
            }
        } catch (Exception e) {
            log.error("获取矿石数量异常", e);
        }
        return -1;
    }

    /**
     * 掘金签到、抽奖、获取矿石总数量
     *
     * @return 任务结果
     */
    public String job() {
        Map<String, String> headers = new HashMap<>(1);
        headers.put("cookie", jueJinConfig.getCookie());

        return String.format("掘金签到结果：%s\n掘金抽奖结果：%s\n掘金矿石数量：%s\n",
                        checkIn(headers),
                        lottery(headers),
                        getOreCount(headers));
    }
}
