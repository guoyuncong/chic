package com.chic.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chic.post.entity.PostTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章标签
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Mapper
public interface PostTagMapper extends BaseMapper<PostTag> {
}
