package com.lee.automatic.weixin;

/**
 * 机器人数量枚举，和配置中应用ID数量对应
 *
 * @author Lee
 */
public enum RobotEnum {
    /**
     * 自动助手机器人
     */
    AUTOMATIC(0),
    ;

    private int value;

    RobotEnum( int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
