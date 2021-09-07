package com.chic.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chic.post.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 分类
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 获取最小的code
     *
     * @return code
     */
    String selectMaxCode(@Param("parentId") String parentId);
}
