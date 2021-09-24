package com.chic.post.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chic.post.entity.PostCategory;
import com.chic.post.mapper.PostCategoryMapper;
import com.chic.post.service.CategoryService;
import com.chic.post.service.PostCategoryService;
import com.chic.post.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 文章分类
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Service
public class PostCategoryServiceImpl extends ServiceImpl<PostCategoryMapper, PostCategory> implements PostCategoryService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void savePostCategories(String postId, Set<String> categoryIds) {
        if (CollUtil.isEmpty(categoryIds) || StrUtil.isEmpty(postId)) {
            return;
        }
        // 删除已存在
        LambdaQueryWrapper<PostCategory> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(PostCategory::getPostId, postId);
        this.baseMapper.delete(queryWrapper);
        // 新增
        List<PostCategory> postCategories = categoryIds.stream().map(categoryId -> {
            PostCategory postCategory = new PostCategory();
            postCategory.setPostId(postId);
            postCategory.setCategoryId(categoryId);
            return postCategory;
        }).collect(Collectors.toList());
        this.saveBatch(postCategories);
    }

    @Override
    public void deletePostCategory(String postId, String categoryId) {
        LambdaQueryWrapper<PostCategory> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StrUtil.isNotEmpty(postId), PostCategory::getPostId, postId);
        queryWrapper.eq(StrUtil.isNotEmpty(categoryId), PostCategory::getPostCategoryId, categoryId);
        this.baseMapper.delete(queryWrapper);
    }

    @Override
    public List<CategoryVO> query4postCategory(String postId) {
        LambdaQueryWrapper<PostCategory> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(PostCategory::getPostId, postId);
        List<PostCategory> postCategories = this.baseMapper.selectList(queryWrapper);
        List<String> categoryIds = postCategories.stream().map(PostCategory::getCategoryId).collect(Collectors.toList());
        return CollUtil.isEmpty(categoryIds) ? Collections.EMPTY_LIST : categoryService.listCategory(null, categoryIds);
    }
}
