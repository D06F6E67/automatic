package com.lee.automatic.telecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 电信云宠物 任务 请求参数
 *
 * @author Lee
 */
@Data
@AllArgsConstructor
public class TaskReq {

    /**
     * 任务ID
     */
    private String taskId;
    /**
     * aid
     */
    private String aid;
}