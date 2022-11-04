package com.lee.automatic.juejin.game.model;

import com.lee.automatic.juejin.game.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 海底掘金开始 请求
 * @author Lee
 */
@Data
@AllArgsConstructor
public class SeaStartReq {

    /**
     * 角色ID
     * <pre>{@link RoleEnum}</pre>
     */
    private Integer roleId;
}