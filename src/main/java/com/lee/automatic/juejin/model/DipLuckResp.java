package com.lee.automatic.juejin.model;

import lombok.Data;

/**
 * 沾运气结果响应
 *
 * @author Lee
 */
@Data
public class DipLuckResp {

    /**
     * 沾运气次数
     */
    private Integer dipAction;
    /**
     * 有无沾运气
     */
    private Boolean hasDip;
    /**
     * 运气数量
     */
    private Integer totalValue;
    /**
     * 沾运气数量
     */
    private Integer dipValue;
}