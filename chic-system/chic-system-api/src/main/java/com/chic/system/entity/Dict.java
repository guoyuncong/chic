package com.chic.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chic.mybatis.entity.Entity;
import lombok.Data;

/**
 * 字典
 *
 * @author: yc
 * @date: 2021-07-02
 */
@Data
@TableName("sys_dict")
public class Dict extends Entity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String dictId;

    /**
     * 分类code
     */
    private String categoryCode;

    /**
     * 字典code
     */
    private String dictCode;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;
}
