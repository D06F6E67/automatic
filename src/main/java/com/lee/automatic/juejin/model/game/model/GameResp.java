package com.lee.automatic.juejin.model.game.model;

import lombok.Data;

/**
 * 游戏相应
 *
 * @author Lee
 */
@Data
public class GameResp<T> {

    /**
     * 相应数据
     */
    private T data;
}