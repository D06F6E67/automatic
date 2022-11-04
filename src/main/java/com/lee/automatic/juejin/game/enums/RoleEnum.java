package com.lee.automatic.juejin.game.enums;

/**
 * 角色信息
 *
 * @author Lee
 */
public enum RoleEnum {

    /**
     * 增加跳跃4步
     */
    YOYO(1),
    /**
     * 矿石20%加成
     */
    CLICK(2),
    /**
     * 上下左右各加2步
     */
    HAWKING(3);

    private Integer id;

    RoleEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
