package com.chic.post.vo;

import com.chic.post.enums.PostStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章
 *
 * @author: yc
 * @date: 2021-07-01
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
     * 封面
     */
    private String thumbnail;

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
     * 格式化后内容
     */
    private String formatContent;

    /**
     * 分类
     */
    private List<CategoryVO> categories;

    /**
     * 标签
     */
    private List<TagVO> tags;

    /**
     * 状态翻译
     */
    private String statusTrans;

    public String getStatusTrans() {
        if (status != null) {
            PostStatusEnum postStatusEnum = PostStatusEnum.of(status);
            return postStatusEnum == null ? null : postStatusEnum.getTrans();
        }
        return statusTrans;
    }
}
