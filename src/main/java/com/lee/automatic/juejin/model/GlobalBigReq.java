package com.lee.automatic.juejin.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 围观大奖请求
 *
 * @author Lee
 */
@Data
public class GlobalBigReq {

    @JSONField(name = "page_no")
    private Integer pageNo;
    @JSONField(name = "page_size")
    private Integer pageSize;

    public GlobalBigReq() {
        this.pageNo = 1;
        this.pageSize = 5;
    }
}