package com.chic.post.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chic.mybatis.entity.Entity;
import lombok.Data;

/**
 * 文章分类表
 * @author: yc
 * @date: 2021-05-27
 */
@Data
@TableName("post_category")
public class PostCategory extends Entity {

    /**
     * 主键ID
     */
    @TableId(value = "post_category_id", type = IdType.ASSIGN_UUID)
    private String postCategoryId;

    /**
     * 文章ID
     */
    private String postId;

    /**
     * 分类ID
     */
    private String categoryId;
}
