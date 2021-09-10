package com.chic.system.base.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 配置属性列表
 *
 * @author: yc
 * @date: 2021-09-10
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OptionProperties {

    /**
     * Blog locale.
     */
    BLOG_LOCALE("blog_locale", String.class, ""),

    /**
     * Blog title.
     */
    BLOG_TITLE("blog_title", String.class, ""),

    /**
     * Blog logo.
     */
    BLOG_LOGO("blog_logo", String.class, ""),

    /**
     * Blog url.
     */
    BLOG_URL("blog_url", String.class, ""),

    /**
     * Blog favicon.
     */
    BLOG_FAVICON("blog_favicon", String.class, ""),

    /**
     * Blog footer info.
     */
    BLOG_FOOTER_INFO("blog_footer_info", String.class, ""),
    ;

    /**
     * setting 表中对应的 key 值，如果不存在使用 SettingProperties 中默认值
     */
    private final String value;

    /**
     * 值类型
     */
    private final Class<?> type;

    /**
     * 默认值
     */
    private final String defaultValue;



}
