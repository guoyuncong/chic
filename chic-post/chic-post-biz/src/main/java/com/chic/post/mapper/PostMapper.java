package com.chic.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chic.post.entity.Post;
import com.chic.post.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 分页查询文章 IDS
     *
     * @param page        分页查询参数
     * @param keyword     关键字
     * @param status
     * @param categoryIds 分类IDS
     * @param tagIds      标签IDS
     * @return Page<String>
     */
    Page<String> selectPostIdByParam(Page page,
                                     @Param("keyword") String keyword,
                                     @Param("status") Integer status,
                                     @Param("categoryIds") List<String> categoryIds,
                                     @Param("tagIds") List<String> tagIds);

    /**
     * 分页查询文章列表【只是简单的信息，并未包含复杂的信息】
     *
     * @param postIds 文章IDS
     * @return Page<PostVO>
     */
    List<PostVO> selectPostSimpleByPostIds(@Param("postIds") List<String> postIds);

}
