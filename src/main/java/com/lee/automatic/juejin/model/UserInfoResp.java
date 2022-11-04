package com.lee.automatic.juejin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息
 *
 * @author Lee
 */
@NoArgsConstructor
@Data
public class UserInfoResp {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 公司
     */
    private String company;
    /**
     * 职位
     */
    private String jobTitle;
    /**
     * 头像
     */
    private String avatarLarge;
}