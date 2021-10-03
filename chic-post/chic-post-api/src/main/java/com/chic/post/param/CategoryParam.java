package com.chic.post.param;

import com.chic.core.group.CreateCheck;
import com.chic.core.group.DeleteCheck;
import com.chic.core.group.UpdateCheck;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 分类【新增|修改|删除】请求参数
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Data
public class CategoryParam {

    /**
     * 主键ID
     */
    @NotBlank(message = "主键ID不能为空", groups = {UpdateCheck.class, DeleteCheck.class})
    private String categoryId;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空", groups = {CreateCheck.class, UpdateCheck.class})
    private String categoryName;

    /**
     * 别名
     */
    private String alias;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 封面图
     */
    private String thumbnail;
}
