package com.chic.system.param;

import com.chic.core.group.CreateCheck;
import com.chic.core.group.DeleteCheck;
import com.chic.core.group.UpdateCheck;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 字典分类请求参数
 *
 * @author: yc
 * @date: 2021-07-02
 */
@Data
public class DictCategoryParam {

    /**
     * 主键ID
     */
    @NotBlank(message = "主键ID不能为空", groups = DeleteCheck.class)
    private String dictCategoryId;

    /**
     * 分类名称
     */
    @NotBlank(message = "主键ID不能为空", groups ={CreateCheck.class, UpdateCheck.class})
    private String categoryName;

    /**
     * 分类code
     */
    @NotBlank(message = "主键ID不能为空", groups ={CreateCheck.class, UpdateCheck.class})
    private String categoryCode;

    /**
     * 描述
     */
    private String description;
}
