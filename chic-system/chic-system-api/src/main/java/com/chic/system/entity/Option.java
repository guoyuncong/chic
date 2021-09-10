package com.chic.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chic.mybatis.entity.Entity;
import lombok.Data;

/**
 * 配置表
 *
 * @author: yc
 * @date: 2021-09-10
 */
@Data
@TableName("options")
public class Option extends Entity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String optionId;

    /**
     * 键值-键
     */
    private String optionKey;

    /**
     * 键值-值
     */
    private String optionValue;
}
