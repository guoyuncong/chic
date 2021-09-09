package com.chic.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chic.system.entity.DictCategory;
import com.chic.system.param.DictCategoryParam;
import com.chic.system.vo.DictCategoryVO;

/**
 * 字典分类
 *
 * @author: yc
 * @date: 2021-07-02
 */
public interface DictCategoryService extends IService<DictCategory> {

    /**
     * 新增字典分类
     *
     * @param dictCategoryParam 请求参数
     * @return 主键ID
     */
    String saveDictCategory(DictCategoryParam dictCategoryParam);

    /**
     * 更新字典分类
     *
     * @param dictCategoryParam 请求参数
     */
    void updateDictCategory(DictCategoryParam dictCategoryParam);

    /**
     * 删除字典
     *
     * @param dictCategoryParam 请求参数
     */
    void deleteDictCategory(DictCategoryParam dictCategoryParam);

    /**
     * 字典分类列表——分页
     *
     * @param page    分页参数
     * @param keyword 关键字
     * @return Page<DictCategoryVO>
     */
    Page<DictCategoryVO> pageDictCategory(Page page, String keyword);
}
