package com.chic.post.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文章状态
 *
 * @author: yc
 * @date: 2021-07-07
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PostStatusEnum {

    /**
     * 1    已发布
     * 2    草稿中
     * 3    回收站
     */
    PUBLISHED(1, "已发布"),
    DRAFT(2, "草稿中"),
    RECYCLE(3, "回收站"),
    ;

    private Integer status;

    private String trans;

    public static PostStatusEnum of(Integer status) {
        for (PostStatusEnum postStatusEnum : values()) {
            if(postStatusEnum.getStatus().equals(status)) {
                return postStatusEnum;
            }
        }
        return null;
    }
}
