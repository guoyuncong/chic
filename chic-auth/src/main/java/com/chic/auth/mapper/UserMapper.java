package com.chic.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chic.auth.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * @author: gyc
 * @date: 2021-12-13 17:24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
