package com.chic.post.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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


    /**
     * 文章状态
     */
    @NotNull(message = "文章状态不能为空")
    @Size(min = 1, max = 3, message = "文章状态数值错误")
    private Integer status;

}
