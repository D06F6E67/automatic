package com.lee.automatic.juejin;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lee.automatic.common.utils.HttpUtils;
import com.lee.automatic.juejin.model.DipLuckResp;
import com.lee.automatic.juejin.model.FreeLotteryResp;
import com.lee.automatic.juejin.model.JueJinResp;
import com.lee.automatic.juejin.model.LotteryResp;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 掘金 签到抽奖
 * <pre><a href="https://blog.csdn.net/qq_35092597/article/details/125157948">参考</a></pre>
 *
 * @author Lee
 */
@Slf4j
@Service
public class JueJinChickInService {

    @Resource
    private JueJinConfig jueJinConfig;

    /**
     * 签到
     *
     * @return 签到结果
     */
    public String checkIn() {
        try {
            Response response = HttpUtils.get(JueJinConfig.TODAY_STATUS_URL, jueJinConfig.getHeaders(), null);
            JueJinResp<Boolean> jueJinResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<Boolean>>(){});

            if (jueJinResp.getData()) {
                return "今日已签到";
            }

            HttpUtils.post(JueJinConfig.CHECK_IN_URL, jueJinConfig.getHeaders(), null);

            return "签到成功";
        } catch (Exception e) {
            log.error("掘金签到失败", e);
            return "签到失败";
        }
    }

    /**
     * 抽奖
     *
     * @return 抽奖结果
     */
    public String lottery() {
        try {
            Response response = HttpUtils.get(JueJinConfig.FREE_LOTTERY_URL, jueJinConfig.getHeaders(), null);
            JueJinResp<FreeLotteryResp> jueJinResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<FreeLotteryResp>>(){});

            if (jueJinResp.getErrNo() != 0) {
                log.error("掘金获取免费抽奖次数失败：{}", jueJinResp.getErrMsg());
                return "获取免费抽奖次数失败";
            }
            if (jueJinResp.getData().getFreeCount() <= 0) {
                return "没有免费抽奖次数";
            }

            Response post = HttpUtils.post(JueJinConfig.LOTTERY_URL, jueJinConfig.getHeaders(), null);
            JueJinResp<LotteryResp> lotteryResp = JSONObject.parseObject(post.body().string(),
                    new TypeReference<JueJinResp<LotteryResp>>() {});

            return lotteryResp.getData().getLotteryName();
        } catch (Exception e) {
            log.error("掘金抽奖失败", e);
            return "抽奖失败";
        }
    }

    /**
     * 沾运气
     *
     * @return 运气总数
     *          <pre>-1：异常</pre>
     */
    public Integer dipLuck() {
        try {
            Response response = HttpUtils.post(JueJinConfig.DIP_LUCKY_URL, jueJinConfig.getHeaders(), null);
            JueJinResp<DipLuckResp> dipLuckResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<DipLuckResp>>() {});

            return dipLuckResp.getData().getTotalValue();
        } catch (Exception e) {
            log.error("掘金沾运气异常", e);
        }
        return -1;
    }

    /**
     * 矿石数量
     *
     * @return 矿石数量
     *          <pre>-1：异常</pre>
     */
    public Integer getOreCount() {
        try {

            Response response = HttpUtils.get(JueJinConfig.ORE_COUNT_URL, jueJinConfig.getHeaders(), null);
            JueJinResp<Integer> jueJinResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<Integer>>() {});

            if (jueJinResp.getErrNo() == 0) {
                return jueJinResp.getData();
            }
            log.error("掘金获取矿石数量失败：{}", jueJinResp.getErrMsg());
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
        return String.format("掘金\n  签到结果：%s\n  抽奖结果：%s\n  运气数量：%s\n  矿石数量：%s\n",
                        checkIn(), lottery(), dipLuck(), getOreCount());
    }
}
