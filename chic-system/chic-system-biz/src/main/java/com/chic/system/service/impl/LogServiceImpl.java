package com.chic.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chic.system.entity.SysLog;
import com.chic.system.mapper.SysLogMapper;
import com.chic.system.service.LogService;
import org.springframework.stereotype.Service;

/**
 * 日志
 *
 * @author: yc
 * @date: 2021-06-02
 */
@Service
public class LogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements LogService {


}
