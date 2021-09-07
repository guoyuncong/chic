package com.chic.post.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chic.core.base.constants.DatabaseDefaultConstants;
import com.chic.core.base.enums.ResultCode;
import com.chic.core.exception.BizException;
import com.chic.mybatis.util.PageConvertUtil;
import com.chic.post.base.enums.PostResultCode;
import com.chic.post.convert.CategoryConvert;
import com.chic.post.entity.Category;
import com.chic.post.mapper.CategoryMapper;
import com.chic.post.param.CategoryParam;
import com.chic.post.service.CategoryService;
import com.chic.post.service.PostCategoryService;
import com.chic.post.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private PostCategoryService postCategoryService;

    @Override
    public String saveCategory(CategoryParam categoryParam) {
        checkCategoryParam(null, categoryParam.getParentId(), categoryParam.getCategoryName());
        Category category = CategoryConvert.INSTANCE.convert2category(categoryParam);
        this.baseMapper.insert(category);
        return category.getCategoryId();
    }

    @Override
    public void updateCategory(CategoryParam categoryParam) {
        checkCategoryParam(categoryParam.getCategoryId(), categoryParam.getParentId(), categoryParam.getCategoryName());
        Category category = CategoryConvert.INSTANCE.convert2category(categoryParam);
        this.baseMapper.updateById(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCategory(String categoryId) {
        // 删除分类
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Category::getCategoryId, categoryId);
        this.baseMapper.delete(queryWrapper);
        // 删除文章分类
        postCategoryService.deletePostCategory(null, categoryId);
    }

    @Override
    public void checkCategoryExist(String categoryId) {
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Category::getCategoryId, categoryId);
        Integer count = this.baseMapper.selectCount(queryWrapper);
        if (count == 0) {
            throw BizException.of(ResultCode.BIZ_DATA_NOT_EXIST);
        }
    }

    @Override
    public Category check4category(String categoryId) {
        Category category = this.baseMapper.selectById(categoryId);
        if (category == null) {
            throw BizException.of(ResultCode.BIZ_DATA_NOT_EXIST);
        }
        return category;
    }

    @Override
    public Page<CategoryVO> pageCategory(Page page, String keyword) {
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StrUtil.isNotBlank(keyword), Category::getCategoryName, keyword);
        queryWrapper.orderByDesc(Category::getCreateTime);
        Page<Category> categoryPage = this.baseMapper.selectPage(page, queryWrapper);
        List<CategoryVO> categoryVOS = CategoryConvert.INSTANCE.convert2categoryVOS(categoryPage.getRecords());
        return PageConvertUtil.convert(categoryPage, categoryVOS);
    }

    @Override
    public List<CategoryVO> listCategory(String keyword, List<String> categoryIds) {
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StrUtil.isNotBlank(keyword), Category::getCategoryName, keyword);
        queryWrapper.in(CollUtil.isNotEmpty(categoryIds), Category::getCategoryId, categoryIds);
        List<Category> categories = this.baseMapper.selectList(queryWrapper);
        return CategoryConvert.INSTANCE.convert2categoryVOS(categories);
    }

    @Override
    public List<CategoryVO> treeCategory() {
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByAsc(Category::getSort);
        // 所有分类
        List<Category> categories = this.baseMapper.selectList(queryWrapper);
        List<CategoryVO> categoryVOS = CategoryConvert.INSTANCE.convert2categoryVOS(categories);
        // 查询一级分类
        List<CategoryVO> parentCategories = categoryVOS.stream()
                .filter(categoryVO -> DatabaseDefaultConstants.DEFAULT_ZERO.equals(categoryVO.getParentId()))
                .collect(Collectors.toList());
        // 转换成树结构
        parentCategories.forEach(parentCategory -> convert2TreeStructure(parentCategory, categoryVOS));
        return parentCategories;
    }

    @Override
    public void checkCategoriesMatch(Set<String> categoryIds) {
        if (CollUtil.isEmpty(categoryIds)) {
            return;
        }
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Category::getCategoryId, categoryIds);
        Integer count = this.baseMapper.selectCount(queryWrapper);
        if (! count.equals(categoryIds.size())) {
            throw BizException.of(PostResultCode.POST_CATEGORY_NOT_MATCH);
        }
    }

    /**
     * 核验参数：
     * ❶ 父级是否存在；
     * ❷ 同级下不允许存在相同的名称（注意：有可能修改自己）
     *
     * @param parentId     父级ID
     * @param categoryName 分类名称
     */
    private void checkCategoryParam(String categoryId, String parentId, String categoryName) {
        // ❶ 父级是否存在；
        if (StrUtil.isEmpty(parentId)) {
            return;
        }
        checkCategoryExist(parentId);
        // ❷ 同级下不允许存在相同的名称
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StrUtil.isNotEmpty(parentId), Category::getParentId, parentId);
        queryWrapper.eq(Category::getCategoryName, categoryName);
        if (StrUtil.isNotEmpty(categoryId)) {
            queryWrapper.ne(Category::getCategoryId, categoryId);
        }
        Integer count = this.baseMapper.selectCount(queryWrapper);
        if (count != 0) {
            throw BizException.of(PostResultCode.CATEGORY_NAME_EXIST);
        }
    }

    /**
     * 分类数据转换为树级结构
     *
     * @param parentCategory 父级
     * @param categoryVOS    所有
     */
    private void convert2TreeStructure(CategoryVO parentCategory, List<CategoryVO> categoryVOS) {
        String categoryId = parentCategory.getCategoryId();
        // 所有下级
        List<CategoryVO> childAll = categoryVOS.stream()
                .filter(categoryVO -> categoryId.equals(categoryVO.getParentId()))
                .collect(Collectors.toList());
        // 递归
        childAll.forEach(categoryVO -> convert2TreeStructure(categoryVO, categoryVOS));
        parentCategory.setChild(childAll);
    }
}
