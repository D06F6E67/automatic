package com.lee.automatic.juejin.model;

import lombok.Data;

import java.util.List;

/**
 * 围观大奖响应
 *
 * @author Lee
 */
@Data
public class LotteriesResp {

    /**
     * 大奖列表
     */
    List<LotterieResp> lotteries;

    /**
     * 总数量
     */
    Integer count;
}

