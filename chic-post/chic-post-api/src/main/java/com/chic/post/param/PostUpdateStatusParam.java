package com.chic.post.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 文章更改状态
 *
 * @author: yc
 * @date: 2021-07-07
 */
@Data
public class PostUpdateStatusParam {

    /**
     * 文章ID
     */
    @NotBlank(message = "文章ID不能为空")
    private String postId;

    /**
     * 状态
     */
    @NotNull(message = "文章状态不能为空")
    private Integer status;
}
