package com.chic.system.vo;


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
public class OptionVO extends Entity {

    /**
     * 主键ID
     */
    private String optionId;

    /**
     * 键值key
     */
    private String key;

    /**
     * value值
     */
    private String value;
}
