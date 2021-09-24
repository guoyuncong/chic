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
import com.chic.post.convert.PostConvert;
import com.chic.post.entity.Post;
import com.chic.post.enums.PostResultCode;
import com.chic.post.enums.PostStatusEnum;
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
        Post oldPost = checkPostExist(postParam.getPostId(), null);
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
        String postId = postParam.getPostId();
        checkPostExist(postId, null);
        // 删除文章，逻辑删除
        this.baseMapper.deleteById(postId);
        // 删除文章分类，关联表-物理删除
        postCategoryService.deletePostCategory(postId, null);
        // 删除文章标签，关联表-物理删除
        postTagService.deletePostTag(postId, null);
    }

    @Override
    public PostVO detailPost(String postId) {
        Post post = checkPostExist(postId, null);
        PostVO postVO = PostConvert.INSTANCE.convert2postVO(post);
        // 查询分类
        List<CategoryVO> categories = postCategoryService.query4postCategory(post.getPostId());
        postVO.setCategories(categories);
        // 查找标签
        List<TagVO> tagVOS = postTagService.query4postTag(post.getPostId());
        postVO.setTags(tagVOS);
        return postVO;
    }

    @Override
    public PostVO clientDetailPost(String abbr) {
        Post post = checkPostExist(null, abbr);
        // 非已发布文章不允许访问详情
        if (! PostStatusEnum.PUBLISHED.getStatus().equals(post.getStatus())) {
            throw BizException.of(PostResultCode.POST_NOT_PUBLISHED);
        }
        PostVO postVO = PostConvert.INSTANCE.convert2postVO(post);
        // 查找标签
        List<TagVO> tagVOS = postTagService.query4postTag(post.getPostId());
        postVO.setTags(tagVOS);
        // 前端使用不返回原始内容
        postVO.setOriginContent(null);
        // 每一次访问，访问次数+1
        post.setVisitNum(post.getVisitNum() + 1);
        this.baseMapper.updateById(post);
        return postVO;
    }

    @Override
    public void editPost(PostEditParam postEditParam) {
        Post post = checkPostExist(postEditParam.getPostId(), null);
        post.setOriginContent(postEditParam.getOriginContent());
        post.setFormatContent(postEditParam.getFormatContent());
        post.setUpdateNum(post.getUpdateNum() + 1);
        post.setStatus(postEditParam.getStatus());
        this.baseMapper.updateById(post);
    }

    @Override
    public Page<PostVO> pagePost(Page page, String keyword, Integer status, String categoryId, String tagId) {
        List<String> categoryIds = null;
        if (StrUtil.isNotEmpty(categoryId)) {
            categoryIds = Arrays.asList(categoryId.split(","));
        }
        List<String> tagIds = null;
        if (StrUtil.isNotEmpty(tagId)) {
            tagIds = Arrays.asList(tagId.split(","));
        }
        Page<String> postIdsPage = this.baseMapper.selectPostIdByParam(page, keyword, status, categoryIds, tagIds);
        List<String> postIds = postIdsPage.getRecords();
        if (CollUtil.isEmpty(postIds)) {
            return PageConvertUtil.convert(postIdsPage, Collections.EMPTY_LIST);
        } else {
            List<PostVO> postVOS = this.baseMapper.selectPostSimpleByPostIds(postIds);
            return PageConvertUtil.convert(postIdsPage, postVOS);
        }
    }

    @Override
    public void updateStatus(PostUpdateStatusParam param) {
        Post post = checkPostExist(param.getPostId(), null);
        post.setStatus(param.getStatus());
        this.baseMapper.updateById(post);
    }

    /**
     * 核验文章是否存在
     *
     * @param postId 主键ID
     */
    private Post checkPostExist(String postId, String abbr) {
        LambdaQueryWrapper<Post> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StrUtil.isNotEmpty(postId), Post::getPostId, postId);
        queryWrapper.eq(StrUtil.isNotEmpty(abbr), Post::getAbbr, abbr);
        Post post = this.baseMapper.selectOne(queryWrapper);
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
