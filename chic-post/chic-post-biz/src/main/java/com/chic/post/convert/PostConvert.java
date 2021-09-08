package com.chic.post.convert;

import com.chic.post.entity.Post;
import com.chic.post.param.PostParam;
import com.chic.post.vo.PostVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章映射类
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Mapper
public interface PostConvert {

    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    /**
     * param -> entity
     *
     * @param postParam param
     * @return entity
     */
    Post convert2post(PostParam postParam);

    /**
     * param & old -> entity
     *
     * @param postParam param
     * @param oldPost   old
     * @return entity
     */
    @Mapping(target = "postId", source = "postParam.postId")
    @Mapping(target = "title", source = "postParam.title")
    @Mapping(target = "abbr", source = "postParam.abbr")
    @Mapping(target = "publishTime", source = "postParam.publishTime")
    @Mapping(target = "digest", source = "postParam.digest")
    @Mapping(target = "keywords", source = "postParam.keywords")
    @Mapping(target = "cover", source = "postParam.cover")
    @Mapping(target = "topFlag", source = "postParam.topFlag")
    @Mapping(target = "commentFlag", source = "postParam.commentFlag")
    @Mapping(target = "originContent", source = "oldPost.originContent")
    @Mapping(target = "formatContent", source = "oldPost.formatContent")
    @Mapping(target = "visitNum", source = "oldPost.visitNum")
    @Mapping(target = "updateNum", source = "oldPost.updateNum")
    @Mapping(target = "createBy", source = "oldPost.createBy")
    @Mapping(target = "createTime", source = "oldPost.createTime")
    @Mapping(target = "version", source = "oldPost.version")
    Post convert2post(PostParam postParam, Post oldPost);

    /**
     * entity -> vo
     *
     * @param post entity
     * @return vo
     */
    PostVO convert2postVO(Post post);

    /**
     * List<entity> -> List<vo>
     *
     * @param posts List<entity>
     * @return List<vo>
     */
    List<PostVO> convert2postVOS(List<Post> posts);


}
