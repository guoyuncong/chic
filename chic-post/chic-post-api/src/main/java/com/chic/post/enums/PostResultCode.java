package com.chic.post.enums;

import com.chic.core.exception.BizResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * post 模块返回错误码【1 开头】
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PostResultCode implements BizResponse {

    /**
     * 分类
     */
    CATEGORY_NAME_EXIST("101000", "分类名称已存在"),

    /**
     * 标签
     */
    TAG_NAME_EXIST("102000", "标签名称已存在"),

    /**
     * 文章
     */
    POST_ABBR_EXIST("103000", "文章别名已存在"),
    POST_TAG_NOT_MATCH("103001", "文章标签不匹配"),
    POST_CATEGORY_NOT_MATCH("103002", "文章分类不匹配"),
    POST_NOT_PUBLISHED("103003", "非已发布文章不允许访问"),

    ;
    private String code;

    private String message;
}
