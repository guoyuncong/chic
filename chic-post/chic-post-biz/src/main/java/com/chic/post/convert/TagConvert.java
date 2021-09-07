package com.chic.post.convert;

import com.chic.post.entity.Tag;
import com.chic.post.param.TagParam;
import com.chic.post.vo.TagVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 标签映射类
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Mapper
public interface TagConvert {

    TagConvert INSTANCE = Mappers.getMapper(TagConvert.class);

    /**
     * param -> entity
     *
     * @param tagParam param
     * @return entity
     */
    Tag convert2tag(TagParam tagParam);

    /**
     * entity -> vo
     *
     * @param tag entity
     * @return vo
     */
    TagVO convert2tagVO(Tag tag);

    /**
     * List<entity> -> List<vo>
     *
     * @param tags List<entity>
     * @return List<vo>
     */
    List<TagVO> convert2tagVOS(List<Tag> tags);
}
