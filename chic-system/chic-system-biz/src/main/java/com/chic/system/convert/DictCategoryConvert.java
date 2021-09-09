package com.chic.system.convert;

import com.chic.system.entity.DictCategory;
import com.chic.system.param.DictCategoryParam;
import com.chic.system.vo.DictCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: yc
 * @date: 2021-07-02
 */
@Mapper
public interface DictCategoryConvert {

    DictCategoryConvert INSTANCE = Mappers.getMapper(DictCategoryConvert.class);

    /**
     * dto -> entity
     *
     * @param dictCategoryParam param
     * @return entity
     */
    DictCategory convert2dictCategory(DictCategoryParam dictCategoryParam);

    /**
     * entity -> vo
     *
     * @param dictCategory entity
     * @return vo
     */
    DictCategoryVO convert2dictCategoryVO(DictCategory dictCategory);

    /**
     * List<entity> -> List<vo>
     *
     * @param dictCategories  List<entity>
     * @return List<vo>
     */
    List<DictCategoryVO> convert2dictCategoryVOS(List<DictCategory> dictCategories);
}
