package com.chic.post.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chic.post.entity.PostCategory;
import com.chic.post.vo.CategoryVO;

import java.util.List;
import java.util.Set;

/**
 * 文章分类
 *
 * @author: yc
 * @date: 2021-05-28
 */
public interface PostCategoryService extends IService<PostCategory> {

    /**
     * 新增文章分类
     *
     * @param postId      文章ID
     * @param categoryIds 分类IDS
     */
    void savePostCategories(String postId, Set<String> categoryIds);

    /**
     * 删除分类
     *
     * @param postId     文章ID
     * @param categoryId 分类ID
     */
    void deletePostCategory(String postId, String categoryId);

    /**
     * 根据 postId 获取文章分类
     *
     * @param postId 文章ID
     * @return List<CategoryVO>
     */
    List<CategoryVO> query4postCategory(String postId);
}
