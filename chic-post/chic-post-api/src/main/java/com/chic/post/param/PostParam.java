package com.chic.post.param;

import com.chic.core.group.CreateCheck;
import com.chic.core.group.DeleteCheck;
import com.chic.core.group.UpdateCheck;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * 文章【新增|修改|删除】请求参数
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Data
public class PostParam {

    /**
     * 主键ID
     */
    @NotBlank(message = "请选择文章", groups = {UpdateCheck.class, DeleteCheck.class})
    private String postId;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = {CreateCheck.class, UpdateCheck.class})
    private String title;

    /**
     * 简称
     */
    @NotBlank(message = "文章别名不能为空", groups = {CreateCheck.class, UpdateCheck.class})
    private String abbr;

    /**
     * 发布时间
     */
    @NotNull(message = "文章别名不能为空", groups = {CreateCheck.class, UpdateCheck.class})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
     * 标签
     */
    private Set<String> tagIds;

    /**
     * 分类
     */
    private Set<String> categoryIds;
}
