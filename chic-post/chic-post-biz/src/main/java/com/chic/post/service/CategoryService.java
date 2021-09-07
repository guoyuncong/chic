package com.chic.post.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chic.post.entity.Category;
import com.chic.post.param.CategoryParam;
import com.chic.post.vo.CategoryVO;

import java.util.List;
import java.util.Set;

/**
 * 分类
 *
 * @author: yc
 * @date: 2021-05-28
 */
public interface CategoryService extends IService<Category> {

    /**
     * 新增分类
     *
     * @param categoryParam 请求参数
     * @return categoryId
     */
    String saveCategory(CategoryParam categoryParam);

    /**
     * 更新分类
     *
     * @param categoryParam 请求参数
     */
    void updateCategory(CategoryParam categoryParam);

    /**
     * 删除分类
     *
     * @param categoryId 分类ID
     */
    void deleteCategory(String categoryId);

    /**
     * 核验分类是否存在
     *
     * @param categoryId 分类ID
     * @return Category
     */
    void checkCategoryExist(String categoryId);

    /**
     * 核验分类是否存在，若存在返回
     *
     * @param categoryId 分类ID
     * @return Category
     */
    Category check4category(String categoryId);

    /**
     * 分类列表—分页
     *
     * @param page    分页参数
     * @param keyword 关键字
     * @return Page<CategoryVO>
     */
    Page<CategoryVO> pageCategory(Page page, String keyword);

    /**
     * 分页列表—不分页
     *
     * @param keyword 关键字
     * @return List<CategoryVO>
     */
    List<CategoryVO> listCategory(String keyword, List<String> categoryIds);

    /**
     * 分类树结构
     *
     * @return List<CategoryVO>
     */
    List<CategoryVO> treeCategory();

    /**
     * 核验分类是否匹配
     *
     * @param categoryIds 分类IDS
     */
    void checkCategoriesMatch(Set<String> categoryIds);
}
