package com.chic.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chic.mybatis.entity.Entity;
import lombok.Data;

/**
 * 字典分类
 *
 * @author: yc
 * @date: 2021-07-02
 */
@Data
@TableName("sys_dict_category")
public class DictCategory extends Entity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String dictCategoryId;

    /**
     * 分类code
     */
    private String categoryCode;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 描述
     */
    private String description;
}
