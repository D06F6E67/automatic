package com.lee.automatic.juejin.model;

import lombok.Data;

/**
 * 免费抽奖信息响应
 *
 * @author Lee
 */
@Data
public class FreeLotteryResp {

    /**
     * 免费次数
     */
    private Integer freeCount;
    /**
     * 抽奖花费
     */
    private Integer pointCost;
}