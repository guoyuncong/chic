package com.chic.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chic.system.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典
 *
 * @author: yc
 * @date: 2021-07-02
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {
}
