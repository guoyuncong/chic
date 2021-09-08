package com.chic.post.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chic.core.exception.BizException;
import com.chic.mybatis.util.PageConvertUtil;
import com.chic.post.convert.TagConvert;
import com.chic.post.entity.Tag;
import com.chic.post.enums.PostResultCode;
import com.chic.post.mapper.TagMapper;
import com.chic.post.param.TagParam;
import com.chic.post.service.PostTagService;
import com.chic.post.service.TagService;
import com.chic.post.vo.TagVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 标签
 *
 * @author: yc
 * @date: 2021-05-28
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    private final PostTagService postTagService;

    @Override
    public String saveTag(TagParam tagParam) {
        // 核验标签名称是否存在
        checkTagNameExist(null, tagParam.getTagName());
        Tag tag = TagConvert.INSTANCE.convert2tag(tagParam);
        this.baseMapper.insert(tag);
        return tag.getTagId();
    }

    @Override
    public void updateTag(TagParam tagParam) {
        // 核验标签名称是否存在
        checkTagNameExist(tagParam.getTagId(), tagParam.getTagName());
        Tag tag = TagConvert.INSTANCE.convert2tag(tagParam);
        this.baseMapper.updateById(tag);
    }

    @Override
    public void deleteTag(TagParam tagParam) {
        // 删除标签
        LambdaQueryWrapper<Tag> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Tag::getTagId, tagParam.getTagIds());
        this.baseMapper.delete(queryWrapper);
        // 删除文章标签
        postTagService.deletePostTag(null, tagParam.getTagIds());
    }

    @Override
    public IPage<TagVO> pageTag(Page page, String keyword) {
        LambdaQueryWrapper<Tag> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StrUtil.isNotBlank(keyword), Tag::getTagName, keyword);
        Page<Tag> tagPage = this.baseMapper.selectPage(page, queryWrapper);
        List<TagVO> tagVOS = TagConvert.INSTANCE.convert2tagVOS(tagPage.getRecords());
        return PageConvertUtil.convert(tagPage, tagVOS);
    }

    @Override
    public List<TagVO> listTag(String keyword, List<String> tagIds) {
        LambdaQueryWrapper<Tag> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StrUtil.isNotBlank(keyword), Tag::getTagName, keyword);
        queryWrapper.in(CollUtil.isNotEmpty(tagIds), Tag::getTagId, tagIds);
        queryWrapper.orderByDesc(Tag::getCreateTime);
        List<Tag> tags = this.baseMapper.selectList(queryWrapper);
        return TagConvert.INSTANCE.convert2tagVOS(tags);
    }

    @Override
    public void checkTagMatch(Set<String> tagIds) {
        if (CollUtil.isEmpty(tagIds)) {
            return;
        }
        LambdaQueryWrapper<Tag> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Tag::getTagId, tagIds);
        Integer count = this.baseMapper.selectCount(queryWrapper);
        if (! count.equals(tagIds.size())) {
            throw BizException.of(PostResultCode.POST_TAG_NOT_MATCH);
        }
    }

    /**
     * 核验标签名称是否存在
     *
     * @param tagId 标签ID
     * @param tagName 标签名称
     */
    private void checkTagNameExist(String tagId, String tagName) {
        LambdaQueryWrapper<Tag> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.ne(StrUtil.isNotEmpty(tagId), Tag::getTagId, tagId);
        queryWrapper.eq(StrUtil.isNotEmpty(tagName), Tag::getTagName, tagName);
        Integer count = this.baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw BizException.of(PostResultCode.TAG_NAME_EXIST);
        }
    }
}
