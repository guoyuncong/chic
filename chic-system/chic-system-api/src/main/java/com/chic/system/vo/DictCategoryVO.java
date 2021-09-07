package com.chic.system.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 字典分类
 *
 * @author: yc
 * @date: 2021-07-02
 */
@Data
public class DictCategoryVO {

    /**
     * 主键ID
     */
    private String dictCategoryId;

    /**
     * 分类code
     */
    private String categoryCode;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 描述
     */
    private String description;

    /**
     * 编辑时间
     */
    private LocalDateTime updateTime;
}
