package com.lee.automatic.juejin.model;

import lombok.Data;

/**
 * 抽奖结果响应
 *
 * @author Lee
 */
@Data
public class LotteryResp {

    /**
     * 奖品名称
     */
    private String lotteryName;
    /**
     * 奖品图片
     */
    private String lotteryImage;
}
