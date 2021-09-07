package com.chic.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chic.system.entity.Dict;

/**
 * 字典
 *
 * @author: yc
 * @date: 2021-07-02
 */
public interface DictService extends IService<Dict> {

    /**
     * 字典分类编码修改
     *
     * @param oldCategoryCode 旧分类编码
     * @param newCategoryCode 新分类编码
     */
    void updateCategoryCode(String oldCategoryCode, String newCategoryCode);

    /**
     * 删除字典
     *
     * @param categoryCode 字典分类code
     */
    void deleteByCategoryCode(String categoryCode);
}
