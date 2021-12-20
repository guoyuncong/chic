package com.chic.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chic.auth.mapper.UserMapper;
import com.chic.auth.model.entity.User;
import com.chic.auth.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户
 * @author: gyc
 * @date: 2021-12-13 17:26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
