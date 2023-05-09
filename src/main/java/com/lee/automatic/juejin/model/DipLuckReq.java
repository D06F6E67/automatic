package com.lee.automatic.juejin.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 沾运气请求
 *
 * @author Lee
 */
@Data
public class DipLuckReq {

    /**
     * 大奖ID
     */
    @JSONField(name = "lottery_history_id")
    private String LotteryHistoryId;

    public DipLuckReq(String lotteryHistoryId) {
        LotteryHistoryId = lotteryHistoryId;
    }
}