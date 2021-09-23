package com.chic.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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

    /**
     * 是否删除：0-未删除，1-已删除
     */
    @TableLogic(value = "false", delval = "true")
    private Boolean deleteFlag;
}