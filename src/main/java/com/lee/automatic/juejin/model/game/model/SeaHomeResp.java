package com.lee.automatic.juejin.model.game.model;

import lombok.Data;

/**
 * 海底掘金游戏主页数据
 * @author Lee
 */
@Data
public class SeaHomeResp {

    /**
     * 活动
     */
    private String activity;
    /**
     * 游戏状态 1游戏中 0未开始
     */
    private Integer gameStatus;
    /*
      游戏信息 暂时不用
     */
    // private SeaGoldResp gameInfo;
}