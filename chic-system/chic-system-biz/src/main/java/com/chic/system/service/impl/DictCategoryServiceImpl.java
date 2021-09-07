/*
package com.chic.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chic.core.exception.BizException;
import com.chic.mybatis.util.PageConvertUtil;
import com.chic.system.base.enums.SysResultCode;
import com.chic.system.convert.DictCategoryConvert;
import com.chic.system.entity.DictCategory;
import com.chic.system.mapper.DictCategoryMapper;
import com.chic.system.param.DictCategoryParam;
import com.chic.system.service.DictCategoryService;
import com.chic.system.service.DictService;
import com.chic.system.vo.DictCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

*/
/**
 * 字典分类
 *
 * @author: yc
 * @date: 2021-07-02
 *//*

@Service
public class DictCategoryServiceImpl extends ServiceImpl<DictCategoryMapper, DictCategory> implements DictCategoryService {

    @Autowired
    private DictService dictService;

    @Override
    public String saveDictCategory(DictCategoryParam dictCategoryParam) {
        // 核验分类编码是否存在
        checkCategoryCodeExist(null, dictCategoryParam.getCategoryCode());
        DictCategory dictCategory = DictCategoryConvert.INSTANCE.convert2dictCategory(dictCategoryParam);
        this.baseMapper.insert(dictCategory);
        return dictCategory.getDictCategoryId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDictCategory(DictCategoryParam dictCategoryParam) {
        String dictCategoryId = dictCategoryParam.getDictCategoryId();
        String categoryCode = dictCategoryParam.getCategoryCode();
        // 核验分类是否存在
        DictCategory oldDictCategory = checkDictCategoryExist(dictCategoryId);
        // 核验分类编码是否存在
        checkCategoryCodeExist(dictCategoryId, categoryCode);
        // 替换下级字典
        dictService.updateCategoryCode(oldDictCategory.getCategoryCode(), categoryCode);
        // 更新字典分类
        DictCategory dictCategory = DictCategoryConvert.INSTANCE.convert2dictCategory(dictCategoryParam);
        this.baseMapper.updateById(dictCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictCategory(DictCategoryParam dictCategoryParam) {
        // 核验分类是否存在
        DictCategory dictCategory = checkDictCategoryExist(dictCategoryParam.getDictCategoryId());
        // 删除字典分类
        this.baseMapper.deleteById(dictCategory.getDictCategoryId());
        // 删除字典
        dictService.deleteByCategoryCode(dictCategory.getCategoryCode());
    }

    @Override
    public Page<DictCategoryVO> pageDictCategory(Page page, String keyword) {
        LambdaQueryWrapper<DictCategory> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StrUtil.isNotBlank(keyword), DictCategory::getCategoryName, keyword);
        Page<DictCategory> dictCategoryPage = this.baseMapper.selectPage(page, queryWrapper);
        List<DictCategoryVO> dictCategoryVOS = DictCategoryConvert.INSTANCE.convert2dictCategoryVOS(dictCategoryPage.getRecords());
        return PageConvertUtil.convert(dictCategoryPage, dictCategoryVOS);
    }

    */
/**
     * 核验分类是否存在
     * 
     * @param dictCategoryId 主键ID
     *//*

    private DictCategory checkDictCategoryExist(String dictCategoryId) {
        DictCategory dictCategory = this.baseMapper.selectById(dictCategoryId);
        if (dictCategory == null) {
            throw BizException.of(SysResultCode.DICT_CATEGORY_NOT_EXIST);
        }
        return dictCategory;
    }

    */
/**
     * 核验分类编码是否存在
     *
     * @param dictCategoryId 分类ID
     * @param categoryCode   分类编码
     *//*

    private void checkCategoryCodeExist(String dictCategoryId, String categoryCode) {
        LambdaQueryWrapper<DictCategory> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictCategory::getCategoryCode, categoryCode);
        queryWrapper.ne(StrUtil.isNotEmpty(dictCategoryId), DictCategory::getDictCategoryId, dictCategoryId);
        Integer count = this.baseMapper.selectCount(queryWrapper);
        if (count != 0) {
            throw BizException.of(SysResultCode.DICT_CATEGORY_CODE_EXIST);
        }
    }
}
*/
