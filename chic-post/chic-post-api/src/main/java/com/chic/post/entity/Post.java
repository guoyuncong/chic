package com.chic.post.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chic.mybatis.entity.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 文章表
 *
 * @author: yc
 * @date: 2021-05-27
 */
@Data
@TableName("post")
public class Post extends Entity {

    /**
     * 主键ID
     */
    @TableId(value = "post_id", type = IdType.ASSIGN_UUID)
    private String postId;

    /**
     * 标题
     */
    private String title;

    /**
     * 别名
     */
    private String abbr;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 摘要
     */
    private String digest;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 原始内容
     */
    private String originContent;

    /**
     * 格式化后内容
     */
    private String formatContent;

    /**
     * 封面
     */
    private String cover;

    /**
     * 是否置顶
     */
    private Boolean topFlag;

    /**
     * 是否开启评论
     */
    private Boolean commentFlag;

    /**
     * 访问次数
     */
    private Long visitNum;

    /**
     * 修改次数
     */
    private Integer updateNum;
}
