package com.lee.automatic.juejin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lee.automatic.common.utils.HttpUtils;
import com.lee.automatic.juejin.config.JueJinConfig;
import com.lee.automatic.juejin.model.DipLuckResp;
import com.lee.automatic.juejin.model.FreeLotteryResp;
import com.lee.automatic.juejin.model.JueJinResp;
import com.lee.automatic.juejin.model.LotteryResp;
import com.lee.automatic.juejin.service.JueJinService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 掘金 抽奖、沾运气
 *
 * @author Lee
 */
@Slf4j
@Service
public class JueJinDipLuckServiceImpl implements JueJinService, Ordered {

    @Resource
    private JueJinConfig jueJinConfig;

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


    @Override
    public String job() {
        return String.format("  抽奖结果：%s\n  运气数量：%s\n", lottery(), dipLuck());
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
