package com.chic.core.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 参数核验异常信息类
 *      主要用于 @Validated 参数核验时，未通过核验返回前端
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidErrorDataDTO {

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性值
     */
    private Object value;

    /**
     * 信息
     */
    private String message;
}
