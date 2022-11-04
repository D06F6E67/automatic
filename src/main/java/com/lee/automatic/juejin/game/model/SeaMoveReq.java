package com.lee.automatic.juejin.game.model;

import com.lee.automatic.juejin.game.enums.DirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 海底掘金移动 请求
 * @author Lee
 */
@Data
@AllArgsConstructor
public class SeaMoveReq {

    /**
     * 移动命令 例：{"command":["D","L",{"times":2,"command":["U"]}]} 下->左->上2 未使用times循环步数
     */
    private List<DirectionEnum> command;
}