package com.chic.post.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chic.post.entity.PostTag;
import com.chic.post.vo.TagVO;

import java.util.List;
import java.util.Set;

/**
 * 文章标签
 *
 * @author: yc
 * @date: 2021-05-28
 */
public interface PostTagService extends IService<PostTag> {

    /**
     * 新增文章标签
     *
     * @param postId 文章ID
     * @param tagIds 标签IDS
     */
    void savePostTag(String postId, Set<String> tagIds);

    /**
     * 删除文章标签
     *
     * @param postId 文章ID
     * @param tagIds 标签IDS
     */
    void deletePostTag(String postId, Set<String> tagIds);

    /**
     * 查找文章标签
     *
     * @param postId 文章ID
     * @return List<TagVO>
     */
    List<TagVO> query4postTag(String postId);
}
