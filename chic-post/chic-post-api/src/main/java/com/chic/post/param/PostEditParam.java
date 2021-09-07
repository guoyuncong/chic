package com.chic.post.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 编辑文章参数
 *
 * @author: yc
 * @date: 2021-07-05
 */
@Data
public class PostEditParam {

    /**
     * 主键ID
     */
    @NotBlank(message = "主键ID不能为空")
    private String postId;

    /**
     * 原始内容
     */
    private String originContent;

    /**
     * 格式化后内容
     */
    private String formatContent;

}
