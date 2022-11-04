package com.lee.automatic.juejin.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 海底掘金登陆 请求
 * @author Lee
 */
@Data
@AllArgsConstructor
public class SeaLoginReq {

    /**
     * 用户名
     */
    private String name;
}