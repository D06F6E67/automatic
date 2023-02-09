package com.lee.automatic.juejin.model.game.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 海底掘金游戏结束数据
 * @author Lee
 */
@NoArgsConstructor
@Data
public class SeaEndResp {

    /**
     * 本局获得钻石
     */
    private Integer realDiamond;
    /**
     *
     */
    private Integer picoDiamond;
    /**
     * 今天获得矿石
     */
    private Integer todayDiamond;
    /**
     * 今日矿石最大限制
     */
    private Integer todayLimitDiamond;
}