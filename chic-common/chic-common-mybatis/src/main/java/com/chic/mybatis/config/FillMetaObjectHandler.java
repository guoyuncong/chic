package com.chic.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.chic.mybatis.constants.DatabaseDefaultConstants;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * MyBatis-Plus 自定义字段自动填充处理类
 *
 * @author: yc
 * @date: 2021-06-08
 */
public class FillMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        // 填充创建时间
        if (metaObject.hasSetter(DatabaseDefaultConstants.CREATE_TIME)) {
            this.setFieldValByName(DatabaseDefaultConstants.CREATE_TIME, now, metaObject);
        }
        // 填充更新时间
        if (metaObject.hasSetter(DatabaseDefaultConstants.UPDATE_TIME)) {
            this.setFieldValByName(DatabaseDefaultConstants.UPDATE_TIME, now, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 填充更新时间
        if (metaObject.hasSetter(DatabaseDefaultConstants.UPDATE_TIME)) {
            this.setFieldValByName(DatabaseDefaultConstants.UPDATE_TIME, LocalDateTime.now(), metaObject);
        }
    }
}
