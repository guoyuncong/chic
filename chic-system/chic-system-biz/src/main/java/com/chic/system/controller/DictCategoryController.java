package com.chic.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chic.core.base.model.vo.R;
import com.chic.core.group.CreateCheck;
import com.chic.core.group.DeleteCheck;
import com.chic.core.group.UpdateCheck;
import com.chic.system.param.DictCategoryParam;
import com.chic.system.service.DictCategoryService;
import com.chic.system.vo.DictCategoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 字典分类
 *
 * @author: yc
 * @date: 2021-07-02
 */

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("dict/category")
public class DictCategoryController {

    private final DictCategoryService dictCategoryService;

    /**
     * 新增字典分类
     *
     * @param dictCategoryParam 文章请求参数
     * @return postId
     */

    @PostMapping("save")
    public R saveDictCategory(@RequestBody @Validated(CreateCheck.class) DictCategoryParam dictCategoryParam) {
        String dictCategoryId = dictCategoryService.saveDictCategory(dictCategoryParam);
        return R.ofSuccess(dictCategoryId);
    }


    /**
     * 新增字典分类
     *
     * @param dictCategoryParam 文章请求参数
     * @return void
     */

    @PostMapping("update")
    public R updateDictCategory(@RequestBody @Validated(UpdateCheck.class) DictCategoryParam dictCategoryParam) {
        dictCategoryService.updateDictCategory(dictCategoryParam);
        return R.ofSuccess();
    }

    /**
     * 删除字典分类
     *
     * @param dictCategoryParam 文章请求参数
     * @return void
     */

    @PostMapping("delete")
    public R deleteDictCategory(@RequestBody @Validated(DeleteCheck.class) DictCategoryParam dictCategoryParam) {
        dictCategoryService.deleteDictCategory(dictCategoryParam);
        return R.ofSuccess();
    }

    /**
     * 字典分类列表——分页
     *
     * @param page    分页参数
     * @param keyword 关键字
     * @return Page<DictCategoryVO>
     */

    @GetMapping("page")
    public R pageDictCategory(Page page, String keyword) {
        Page<DictCategoryVO> dictCategoryVOPage = dictCategoryService.pageDictCategory(page, keyword);
        return R.ofSuccess(dictCategoryVOPage);
    }
}

