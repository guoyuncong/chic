package com.chic.post.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chic.mybatis.entity.Entity;
import lombok.Data;

/**
 * 标签表
 * @author: yc
 * @date: 2021-05-27
 */
@Data
@TableName("tag")
public class Tag extends Entity {

    /**
     * 主键ID
     */
    @TableId(value = "tag_id", type = IdType.ASSIGN_UUID)
    private String tagId;

    /**
     * 标签名称
     */
    private String tagName;
}
