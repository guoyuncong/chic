package com.chic.auth.model.entity;

import com.chic.mybatis.entity.Entity;
import lombok.Data;

/**
 * 用户
 * @author: gyc
 * @date: 2021-12-13 17:13
 */
@Data
public class User extends Entity {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

}
