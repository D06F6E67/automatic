package com.lee.automatic.juejin.game.model;

import lombok.Data;

import java.util.List;

/**
 * 海底掘金游戏数据
 *
 * @author Lee
 */
@Data
public class SeaGoldResp {

    /**
     * 地图ID
     */
    private Integer seed;
    /**
     * 游戏ID
     */
    private String gameId;
    /**
     * 地图数据
     *  <pre>
     *     0 空地 4 贝壳 5 水母 6 石头(障碍)
     *
     * 1开头，前两位对应下方，后面数字代表珍珠数量。如1011 获得上步数+1 珍珠数量+11
     *     10 上 11 下 12 左 13 右 14 跳跃 15 重复
     *
     * 2开头为矿石 运算规则不详
     *  </pre>
     */
    private List<Integer> mapData;
    /**
     * 步数数据
     */
    private SeaStepData blockData;
    /**
     * 所在位置
     */
    private SeaPosition curPos;
}