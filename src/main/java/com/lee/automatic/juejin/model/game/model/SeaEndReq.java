package com.lee.automatic.juejin.model.game.model;

import lombok.Data;

/**
 * 海底掘金结束 请求
 * @author Lee
 */
@Data
public class SeaEndReq {

    /**
     * 固定值1
     */
    private Integer isButton;

    public SeaEndReq() {
        this.isButton = 1;
    }
}
