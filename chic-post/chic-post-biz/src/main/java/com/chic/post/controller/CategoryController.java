package com.chic.post.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chic.core.base.model.vo.R;
import com.chic.core.group.CreateCheck;
import com.chic.core.group.DeleteCheck;
import com.chic.core.group.UpdateCheck;
import com.chic.post.param.CategoryParam;
import com.chic.post.service.CategoryService;
import com.chic.post.vo.CategoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 新增分类
     *
     * @param categoryParam 文章请求参数
     * @return categoryId
     */
    @PostMapping("save")
    public R saveCategory(@RequestBody @Validated(CreateCheck.class) CategoryParam categoryParam) {
        String categoryId = categoryService.saveCategory(categoryParam);
        return R.ofSuccess(categoryId);
    }

    /**
     * 更新分类
     *
     * @param categoryParam 文章请求参数
     * @return void
     */
    @PostMapping("update")
    public R updateCategory(@RequestBody @Validated(UpdateCheck.class) CategoryParam categoryParam) {
        categoryService.updateCategory(categoryParam);
        return R.ofSuccess();
    }

    /**
     * 删除分类【只允许单个删除】
     *
     * @param categoryParam 文章请求参数
     * @return void
     */
    @PostMapping("delete")
    public R deleteCategory(@RequestBody @Validated(DeleteCheck.class) CategoryParam categoryParam) {
        categoryService.deleteCategory(categoryParam.getCategoryId());
        return R.ofSuccess();
    }

    /**
     * 分类列表—分页
     *
     * @param page    分页参数
     * @param keyword 关键字
     * @return Page<CategoryVO>
     */
    @GetMapping("page")
    public R pageCategory(Page page, String keyword) {
        Page<CategoryVO> categoryVOPage = categoryService.pageCategory(page, keyword);
        return R.ofSuccess(categoryVOPage);
    }

    /**
     * 分类列表—不分页
     *
     * @param keyword 关键字
     * @return List<CategoryVO>
     */
    @GetMapping("list")
    public R listCategory(String keyword, List<String> categoryIds) {
        List<CategoryVO> categoryVOS = categoryService.listCategory(keyword, categoryIds);
        return R.ofSuccess(categoryVOS);
    }

    /**
     * 分类树结构
     *
     * @return List<CategoryVO>
     */
    @GetMapping("tree")
    public R treeCategory() {
        List<CategoryVO> categoryVOPage = categoryService.treeCategory();
        return R.ofSuccess(categoryVOPage);
    }


}
