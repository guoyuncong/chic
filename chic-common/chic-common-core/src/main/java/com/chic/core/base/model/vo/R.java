package com.chic.core.base.model.vo;

import com.chic.core.base.enums.ResultCode;
import com.chic.core.exception.BizResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 响应结果实体类
 * @author: yc
 * @date: 2021-05-25
 */
@Getter
@ToString
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public final class R<T> {

    /**
     * 返回码
     */
    private final String code;

    /**
     * 返回信息
     */
    private final String msg;

    /**
     * 数据
     */
    private final T data;

    public static <T> R<T> ofSuccess(T detail) {
        return of(ResultCode.SUCCESS, detail);
    }

    public static <T> R<T> ofSuccess() {
        return of(ResultCode.SUCCESS);
    }

    public static <T> R<T> of(BizResponse response, T data) {
        return new R<>(response.getCode(), response.getMessage(), data);
    }

    public static <T> R<T> of(BizResponse response) {
        return new R<>(response.getCode(), response.getMessage(), null);
    }
}
