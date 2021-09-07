package com.chic.system.base.enums;

import com.chic.core.exception.BizResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回码
 *
 * @author: yc
 * @date: 2021-07-02
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SysResultCode implements BizResponse {

    /**
     * 字典分类
     */
    DICT_CATEGORY_CODE_EXIST("201000", "字典分类编码已存在"),
    DICT_CATEGORY_NOT_EXIST("201000", "字典分类不存在"),

    ;
    private String code;
    private String message;
}
