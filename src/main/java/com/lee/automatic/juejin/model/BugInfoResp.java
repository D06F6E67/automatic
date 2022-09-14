package com.lee.automatic.juejin.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * bug信息
 *
 * @author Lee
 */
@Data
public class BugInfoResp {

    /**
     * bug类型
     */
    @JSONField(name = "bug_type")
    private Integer bugType;
    /**
     * bug时间
     */
    @JSONField(name = "bug_time")
    private Integer bugTime;
}
