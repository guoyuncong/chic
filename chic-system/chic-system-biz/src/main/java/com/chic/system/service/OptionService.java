package com.chic.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chic.system.entity.Option;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

/**
 * 配置项
 *
 * @author: yc
 * @date: 2021-09-10
 */
public interface OptionService extends IService<Option> {

    /**
     * 存储配置项
     *
     * @param optionParam 配置项map
     */
    void saveOptions(Map<String, Object> optionParam);

    /**
     * 获取当前服务器地址
     *
     * @return 获取博客地址
     */
    String getBlogBaseUrl();

    /**
     * 获取所有配置项
     *
     * @param keys 键值
     * @return Map<String, String>
     */
    Map<String, String> listOption(@Nullable List<String> keys);
}
