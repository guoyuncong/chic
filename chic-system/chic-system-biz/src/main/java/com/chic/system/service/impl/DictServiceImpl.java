package com.chic.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chic.system.entity.Dict;
import com.chic.system.mapper.DictMapper;
import com.chic.system.service.DictService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典
 *
 * @author: yc
 * @date: 2021-07-02
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public void updateCategoryCode(String oldCategoryCode, String newCategoryCode) {
        if (oldCategoryCode.equals(newCategoryCode)) {
            return;
        }
        LambdaQueryWrapper<Dict> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Dict::getCategoryCode, oldCategoryCode);
        List<Dict> dict = this.baseMapper.selectList(queryWrapper);
        dict.forEach(data -> {
            String dictCode = data.getDictCode();
            String tail = dictCode.substring(oldCategoryCode.length());
            dictCode = StrUtil.concat(true, newCategoryCode, tail);
            data.setDictCode(dictCode);
        });
        this.updateBatchById(dict);
    }

    @Override
    public void deleteByCategoryCode(String categoryCode) {
        LambdaQueryWrapper<Dict> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Dict::getCategoryCode, categoryCode);
        this.baseMapper.delete(queryWrapper);
    }
}
