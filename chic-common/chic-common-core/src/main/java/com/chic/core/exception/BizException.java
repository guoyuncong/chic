package com.chic.core.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author: yc
 * @date: 2021-05-25
 */
@Getter
public class BizException extends RuntimeException {

    private BizResponse bizResponse;

    private Object data;

    /**
     * 私有
     *
     * @param response 返回结果
     */
    private BizException(BizResponse response) {
        super(response.getMessage());
        this.bizResponse = response;
    }

    /**
     * 私有
     *
     * @param response 返回结果
     * @param data     返回数据
     */
    private BizException(BizResponse response, Object data) {
        super(response.getMessage());
        this.bizResponse = response;
        this.data = data;
    }

    /**
     * 静态方法
     *
     * @param response 返回结果
     * @return BizException
     */
    public static BizException of(BizResponse response) {
        return new BizException(response);
    }

    /**
     * 静态方法
     *
     * @param response 返回结果
     * @param data     返回数据
     * @return BizException
     */
    public static BizException of(BizResponse response, Object data) {
        return new BizException(response, data);
    }
}
