package com.lee.automatic.juejin.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 活动
 *
 * @author Lee
 */
@Data
@NoArgsConstructor
public class CompetitionResp {

    /**
     * 活动ID
     */
    @JSONField(name = "competition_id")
    private String competitionId;
    /**
     * 拥有bug数量
     */
    private Integer userOwnBug;

    public CompetitionResp(String competitionId) {
        this.competitionId = competitionId;
    }
}
