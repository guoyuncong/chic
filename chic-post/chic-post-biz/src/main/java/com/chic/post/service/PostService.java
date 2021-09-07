package com.chic.post.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chic.post.entity.Post;
import com.chic.post.param.PostEditParam;
import com.chic.post.param.PostParam;
import com.chic.post.param.PostUpdateStatusParam;
import com.chic.post.vo.PostVO;

/**
 * 文章
 *
 * @author: yc
 * @date: 2021-05-28
 */
public interface PostService extends IService<Post> {

    /**
     * 新增文章
     *
     * @param postParam 请求参数
     * @return postId
     */
    String savePost(PostParam postParam);

    /**
     * 设置文章
     *
     * @param postParam 请求参数
     */
    void setPost(PostParam postParam);

    /**
     * 删除文章
     *
     * @param postParam 请求参数
     */
    void deletePost(PostParam postParam);

    /**
     * 文章详情
     *
     * @param abbr   文章简称
     * @param postId 文章ID
     * @return PostVO
     */
    PostVO detailPost(String abbr, String postId);

    /**
     * 编辑文章
     *
     * @param postEditParam 请求参数
     */
    void editPost(PostEditParam postEditParam);

    /**
     * 文章列表——分页
     *
     * @param page        分页参数
     * @param keyword     关键字
     * @param categoryIds 分类IDS
     * @param tagIds      标签IDS
     * @return Page<PostVO>
     */
    Page<PostVO> pagePost(Page page, String keyword, String categoryIds, String tagIds);

    /**
     * 更改状态
     *
     * @param param 请求参数
     */
    void updateStatus(PostUpdateStatusParam param);
}
