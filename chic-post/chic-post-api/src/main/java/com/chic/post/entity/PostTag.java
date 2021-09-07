package com.chic.post.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chic.mybatis.entity.Entity;
import lombok.Data;

/**
 * 文章标签
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Data
@TableName("post_tag")
public class PostTag extends Entity {

    /**
     * 主键ID
     */
    @TableId(value = "post_tag_id", type = IdType.ASSIGN_UUID)
    private String postTagId;

    /**
     * 文章ID
     */
    private String postId;

    /**
     * 标签ID
     */
    private String tagId;
}
