package com.chic.core.base.enums;

import com.chic.core.exception.BizResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResultCode implements BizResponse {

    /**
     * 成功
     */
    SUCCESS("00000", "请求成功"),

    /**
     * 用户请求参数错误
     */
    USER_REQUEST_PARAM_ERROR("00001", "用户请求参数错误"),

    /**
     * 数据不存在或已删除
     */
    BIZ_DATA_NOT_EXIST("00002", "数据不存在或已删除"),

    /**
     * 系统执行出错
     */
    BIZ_SYSTEM_EXECUTE_ERROR("00003", "系统执行出错"),

    ;
    private String code;
    private String message;
}
