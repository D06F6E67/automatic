package com.lee.automatic.telecom.model;

import lombok.Data;

import java.util.List;

/**
 * 电信云宠物 猫粮数量 返回
 *
 * @author Lee
 */
@Data
public class CatFoodResp {

    /**
     * 猫粮信息
     */
    private List<AwardsNum> awardsNum;
    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 猫粮信息
     */
    @Data
    public static class AwardsNum {
        /**
         * 数量
         */
        private Double value;
        /**
         * 虚构类型
         */
        private String fictitiousType;
        /**
         * 虚名
         */
        private Object fictitiousName;
    }
}