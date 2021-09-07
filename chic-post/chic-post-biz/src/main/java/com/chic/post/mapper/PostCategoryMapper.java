package com.chic.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chic.post.entity.PostCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章分类
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Mapper
public interface PostCategoryMapper extends BaseMapper<PostCategory> {
}
