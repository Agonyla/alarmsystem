package com.agony.alarmsystem.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 *
 * @TableName user
 */

@Data
public class UserDTO implements Serializable {


    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 短信
     */
    private String message;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 钉钉ID
     */
    private String dingtalkId;


}