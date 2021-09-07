package com.chic.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.chic.mybatis.config.FillMetaObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-plus 自动配置类
 *
 * @author: yc
 * @date: 2021-06-29
 */
@Configuration(proxyBeanMethods = false)
public class MyBatisAutoConfiguration {

    @Bean
    public FillMetaObjectHandler fillMetaObjectHandler() {
        return new FillMetaObjectHandler();
    }

    /**
     * 分页插件
     *
     * @return PaginationInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }

    @Bean
    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
        return properties -> properties.getGlobalConfig().setBanner(false);
    }
}
