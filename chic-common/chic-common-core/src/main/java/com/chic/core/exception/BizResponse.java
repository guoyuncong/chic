package com.chic.core.exception;

/**
 * 响应
 *
 * @author: yc
 * @date: 2021-05-28
 */
public interface BizResponse {

    /**
     * 返回错误码
     *
     * @return 错误码
     */
    String getCode();

    /**
     * 返回错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
