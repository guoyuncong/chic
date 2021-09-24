package com.chic.post.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chic.post.entity.PostTag;
import com.chic.post.mapper.PostTagMapper;
import com.chic.post.service.PostTagService;
import com.chic.post.service.TagService;
import com.chic.post.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 文章标签
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Service
public class PostTagServiceImpl extends ServiceImpl<PostTagMapper, PostTag> implements PostTagService {

    @Autowired
    private TagService tagService;

    @Override
    public void savePostTag(String postId, Set<String> tagIds) {
        if (CollUtil.isEmpty(tagIds) || StrUtil.isEmpty(postId)) {
            return;
        }
        // 删除已存在
        LambdaQueryWrapper<PostTag> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(PostTag::getPostId, postId);
        this.baseMapper.delete(queryWrapper);
        // 新增
        List<PostTag> postTags = tagIds.stream().map(tagId -> {
            PostTag postTag = new PostTag();
            postTag.setPostId(postId);
            postTag.setTagId(tagId);
            return postTag;
        }).collect(Collectors.toList());
        this.saveBatch(postTags);
    }

    @Override
    public void deletePostTag(String postId, Set<String> tagIds) {
        LambdaQueryWrapper<PostTag> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StrUtil.isNotEmpty(postId), PostTag::getPostId, postId);
        queryWrapper.in(CollUtil.isNotEmpty(tagIds), PostTag::getTagId, tagIds);
        this.baseMapper.delete(queryWrapper);
    }

    @Override
    public List<TagVO> query4postTag(String postId) {
        LambdaQueryWrapper<PostTag> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(PostTag::getPostId, postId);
        List<PostTag> postTags = this.baseMapper.selectList(queryWrapper);
        List<String> tagIds = postTags.stream().map(PostTag::getTagId).distinct().collect(Collectors.toList());
        return CollUtil.isEmpty(tagIds) ? Collections.EMPTY_LIST : tagService.listTag(null, tagIds);
    }
}
