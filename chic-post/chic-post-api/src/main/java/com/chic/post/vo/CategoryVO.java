package com.chic.post.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 分类
 *
 * @author: yc
 * @date: 2021-06-09
 */
@Data
public class CategoryVO {

    /**
     * 主键ID
     */
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
     * 封面图示
     */
    private String thumbnail;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 子级
     */
    private List<CategoryVO> child;
}
