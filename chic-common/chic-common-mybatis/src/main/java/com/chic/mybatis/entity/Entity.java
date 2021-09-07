package com.chic.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: yc
 * @date: 2021-06-29
 */
@Data
public class Entity {

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 由谁创建
     */
    private String createBy;

    /**
     * 由谁修改
     */
    private String updateBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}