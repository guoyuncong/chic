package com.chic.post.convert;

import com.chic.post.entity.Category;
import com.chic.post.param.CategoryParam;
import com.chic.post.vo.CategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 分类映射类
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Mapper
public interface CategoryConvert {

    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    /**
     * param -> entity
     *
     * @param categoryParam param
     * @return entity
     */
    Category convert2category(CategoryParam categoryParam);

    /**
     * entity -> vo
     *
     * @param category entity
     * @return vo
     */
    CategoryVO convert2categoryVO(Category category);

    /**
     * List<entity> -> List<vo>
     *
     * @param categories List<entity>
     * @return List<vo>
     */
    List<CategoryVO> convert2categoryVOS(List<Category> categories);
}
