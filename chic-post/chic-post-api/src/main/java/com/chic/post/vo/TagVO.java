package com.chic.post.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 标签
 *
 * @author: yc
 * @date: 2021-06-09
 */
@Data
public class TagVO {

    /**
     * 主键ID
     */
    private String tagId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
}
