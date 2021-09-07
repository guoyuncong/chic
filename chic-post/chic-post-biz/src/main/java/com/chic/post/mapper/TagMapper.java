package com.chic.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chic.post.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
}
