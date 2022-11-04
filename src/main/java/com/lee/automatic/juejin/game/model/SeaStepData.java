package com.lee.automatic.juejin.game.model;

import lombok.Data;

/**
 * 海底掘金步数数据
 *
 * @author lee
 */
@Data
public class SeaStepData {

    /**
     * 向上剩余步数
     */
    private Integer moveUp;
    /**
     * 向下剩余步数
     */
    private Integer moveDown;
    /**
     * 向左剩余步数
     */
    private Integer moveLeft;
    /**
     * 向右剩余步数
     */
    private Integer moveRight;
    /**
     * 跳跃剩余步数
     */
    private Integer jump;
    /**
     * 重复剩余步数
     */
    private Integer loop;

    /**
     * 向上移动
     * @return 剩余步数
     */
    public Integer moveUp() {
        return --this.moveUp;
    }
    /**
     * 向下移动
     * @return 剩余步数
     */
    public Integer moveDown() {
        return --this.moveDown;
    }
    /**
     * 向左移动
     * @return 剩余步数
     */
    public Integer moveLeft() {
        return --this.moveLeft;
    }
    /**
     * 向右移动
     * @return 剩余步数
     */
    public Integer moveRight() {
        return --this.moveRight;
    }
}