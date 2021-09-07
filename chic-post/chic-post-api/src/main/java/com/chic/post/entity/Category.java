package com.chic.post.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chic.mybatis.entity.Entity;
import lombok.Data;

/**
 * 分类表
 * @author: yc
 * @date: 2021-05-27
 */
@Data
@TableName("category")
public class Category extends Entity {

    /**
     * 主键ID
     */
    @TableId(value = "category_id", type = IdType.ASSIGN_UUID)
    private String categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 别名
     */
    private String alias;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;


}
