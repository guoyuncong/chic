package com.chic.post.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chic.core.base.enums.ResultCode;
import com.chic.core.exception.BizException;
import com.chic.mybatis.util.PageConvertUtil;
import com.chic.post.base.enums.PostResultCode;
import com.chic.post.convert.PostConvert;
import com.chic.post.entity.Post;
import com.chic.post.mapper.PostMapper;
import com.chic.post.param.PostEditParam;
import com.chic.post.param.PostParam;
import com.chic.post.param.PostUpdateStatusParam;
import com.chic.post.service.*;
import com.chic.post.vo.CategoryVO;
import com.chic.post.vo.PostVO;
import com.chic.post.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 文章
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    private PostTagService postTagService;

    @Autowired
    private PostCategoryService postCategoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String savePost(PostParam postParam) {
        // 核验别名是否存在
        String abbr = postParam.getAbbr();
        checkAbbrExist(null, abbr);
        // 核验标签是否匹配
        Set<String> tagIds = postParam.getTagIds();
        tagService.checkTagMatch(tagIds);
        // 核验分类是否匹配
        Set<String> categoryIds = postParam.getCategoryIds();
        categoryService.checkCategoriesMatch(categoryIds);
        // 处理数据
        Post post = PostConvert.INSTANCE.convert2post(postParam);
        this.baseMapper.insert(post);
        String postId = post.getPostId();
        postTagService.savePostTag(postId, tagIds);
        postCategoryService.savePostCategories(postId, categoryIds);
        return postId;
    }

    @Override
    public void setPost(PostParam postParam) {
        Post oldPost = checkPostExist(postParam.getPostId());
        Post post = PostConvert.INSTANCE.convert2post(postParam, oldPost);
        // 核验标签是否匹配
        Set<String> tagIds = postParam.getTagIds();
        tagService.checkTagMatch(tagIds);
        // 核验分类是否匹配
        Set<String> categoryIds = postParam.getCategoryIds();
        categoryService.checkCategoriesMatch(categoryIds);
        // 处理数据
        postTagService.savePostTag(post.getPostId(), tagIds);
        postCategoryService.savePostCategories(post.getPostId(), categoryIds);
        this.updateById(post);
    }

    @Override
    public void deletePost(PostParam postParam) {
        LambdaQueryWrapper<Post> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Post::getPostId, postParam.getPostIds());

    }

    @Override
    public PostVO detailPost(String abbr, String postId) {
        LambdaQueryWrapper<Post> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StrUtil.isNotEmpty(abbr), Post::getAbbr, abbr);
        queryWrapper.eq(StrUtil.isNotEmpty(postId), Post::getPostId, postId);
        Post post = this.baseMapper.selectOne(queryWrapper);
        if (post == null) {
            throw BizException.of(ResultCode.BIZ_DATA_NOT_EXIST);
        }
        PostVO postVO = PostConvert.INSTANCE.convert2postVO(post);
        // 查询分类
        List<CategoryVO> categories = postCategoryService.query4postCategory(Arrays.asList(post.getPostId()));
        postVO.setCategories(categories);
        // 查找标签
        List<TagVO> tagVOS = postTagService.query4postTag(Arrays.asList(post.getPostId()));
        postVO.setTags(tagVOS);
        return postVO;
    }

    @Override
    public void editPost(PostEditParam postEditParam) {
        Post post = checkPostExist(postEditParam.getPostId());
        post.setOriginContent(postEditParam.getOriginContent());
        post.setFormatContent(postEditParam.getFormatContent());
        this.baseMapper.updateById(post);
    }

    @Override
    public Page<PostVO> pagePost(Page page, String keyword, String categoryId, String tagId) {
        List<String> categoryIds = null;
        if (StrUtil.isNotEmpty(categoryId)) {
            categoryIds = Arrays.asList(categoryId.split(","));
        }
        List<String> tagIds = null;
        if (StrUtil.isNotEmpty(tagId)) {
            tagIds = Arrays.asList(tagId.split(","));
        }
        Page<String> postIdsPage = this.baseMapper.selectPostIdByParam(page, keyword, categoryIds, tagIds);
        List<String> postIds = postIdsPage.getRecords();
        if (CollUtil.isEmpty(postIds)) {
            return PageConvertUtil.convert(postIdsPage, Collections.EMPTY_LIST);
        } else {
            List<PostVO> postVOS = this.baseMapper.selectPostByPostIds(postIds);
            return PageConvertUtil.convert(postIdsPage, postVOS);
        }
    }

    @Override
    public void updateStatus(PostUpdateStatusParam param) {
        Post post = checkPostExist(param.getPostId());
        post.setStatus(param.getStatus());
        this.baseMapper.updateById(post);
    }

    /**
     * 核验文章是否存在
     *
     * @param postId 主键ID
     */
    private Post checkPostExist(String postId) {
        Post post = this.baseMapper.selectById(postId);
        if (post == null) {
            throw BizException.of(ResultCode.BIZ_DATA_NOT_EXIST);
        }
        return post;
    }

    /**
     * 核验文章别名是否存在
     *
     * @param postId 文章ID
     * @param abbr   别名
     */
    private void checkAbbrExist(String postId, String abbr) {
        LambdaQueryWrapper<Post> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.ne(StrUtil.isNotEmpty(postId), Post::getPostId, postId);
        queryWrapper.eq(Post::getAbbr, abbr);
        Integer count = this.baseMapper.selectCount(queryWrapper);
        if (count != 0) {
            throw BizException.of(PostResultCode.POST_ABBR_EXIST);
        }
    }
}
