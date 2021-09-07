package com.chic.mybatis.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * Page<entity> -> Page<vo> 转换类
 *
 * @author: yc
 * @date: 2021-06-09
 */
public class PageConvertUtil {

    public static <T> Page<T> convert(Page page, List<T> T) {
        Page<T> iPage = new Page<>();
        iPage.setTotal(page.getTotal());
        iPage.setSize(page.getSize());
        iPage.setCurrent(page.getCurrent());
        iPage.setOrders(page.getOrders());
        iPage.setCountId(page.getCountId());
        iPage.setMaxLimit(page.getMaxLimit());
        iPage.setPages(page.getPages());
        iPage.setRecords(T);
        return iPage;
    }
}
