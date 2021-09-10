package com.chic.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chic.system.base.enums.OptionProperties;
import com.chic.system.entity.Option;
import com.chic.system.mapper.OptionMapper;
import com.chic.system.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import static com.chic.core.base.constants.SymbolConstants.LEFT_FORWARD_SLASH;

/**
 * 配置项
 *
 * @author: yc
 * @date: 2021-09-10
 */
@Service
@RequiredArgsConstructor
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option> implements OptionService {

    private final ApplicationContext applicationContext;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOptions(Map<String, Object> optionParam) {
        // 查询已有的配置并转换为 map
        Map<String, Option> optionMap = this.list().stream().collect(Collectors.toMap(Option::getOptionKey, Function.identity()));
        // 新建配置项集合和更新配置项集合
        List<Option> settingsToCreate = new ArrayList<>();
        List<Option> settingsToUpdate = new ArrayList<>();
        // 遍历 settingParam，存在则覆盖，不存在为新增
        optionParam.forEach((key, value) -> {
            Option option = optionMap.get(key);
            if (Optional.ofNullable(option).isPresent()) {
                option.setOptionValue(value.toString());
                settingsToUpdate.add(option);
            } else {
                option = new Option();
                option.setOptionKey(key);
                option.setOptionValue(value.toString());
                settingsToCreate.add(option);
            }
        });
        // 操作数据库
        super.saveBatch(settingsToCreate);
        super.updateBatchById(settingsToUpdate);
    }

    @Override
    public String getBlogBaseUrl() {
        // 获取博客地址
        String blogUrl = listOption(null).get(OptionProperties.BLOG_URL.getValue());
        // 拼接 博客地址:端口
        if (StrUtil.isNotBlank(blogUrl)) {
            blogUrl = blogUrl.endsWith(LEFT_FORWARD_SLASH) ?  blogUrl.substring(0, blogUrl.length() - LEFT_FORWARD_SLASH.length()) : blogUrl;
        } else {
            // 获取端口号
            String serverPort = applicationContext.getEnvironment().getProperty("server.port", "8080");
            blogUrl = String.format("http://%s:%s", "127.0.0.1", 8030);
        }
        return blogUrl;
    }

    @Override
    public Map<String, String> listOption(List<String> keys) {
        LambdaQueryWrapper<Option> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(CollUtil.isNotEmpty(keys), Option::getOptionKey, keys);
        List<Option> options = this.baseMapper.selectList(queryWrapper);
        return options.stream().collect(Collectors.toMap(Option::getOptionKey, Option::getOptionValue));
    }
}
