package com.lee.automatic.juejin.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 沸点列表请求参数
 *
 * @author Lee
 */
@Data
public class BoilingPointListReq {

    /**
     * 排序类型
     */
    @JSONField(name = "sort_type")
    private Integer sortType;
    private String cursor;
    private Integer limit;

    public BoilingPointListReq() {
        this.sortType = 300;
        this.cursor = "0";
        this.limit = 20;
    }
}
