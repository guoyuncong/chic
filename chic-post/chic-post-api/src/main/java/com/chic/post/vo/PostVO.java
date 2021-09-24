package com.chic.post.vo;

import com.chic.post.enums.PostStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章详情-含有分类和标签
 *
 * @author: yc
 * @date: 2021-09-23
 */
@Data
public class PostVO {

    /**
     * 主键ID
     */
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
     * 封面
     */
    private String thumbnail;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
     * 原始内容(markdown)
     */
    private String originContent;

    /**
     * 格式化后内容(html)
     */
    private String formatContent;

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

    /**
     * 状态翻译
     */
    private String statusTrans;

    /**
     * 分类列表
     */
    private List<CategoryVO> categories;

    /**
     * 标签列表
     */
    private List<TagVO> tags;

    /**
     * 文章状态翻译
     *
     * @return
     */
    public String getStatusTrans() {
        PostStatusEnum postStatusEnum = PostStatusEnum.of(status);
        return postStatusEnum == null ? null : postStatusEnum.getTrans();
    }
}
