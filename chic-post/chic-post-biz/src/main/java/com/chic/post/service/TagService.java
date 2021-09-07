package com.chic.post.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chic.post.entity.Tag;
import com.chic.post.param.TagParam;
import com.chic.post.vo.TagVO;

import java.util.List;
import java.util.Set;

/**
 * 标签
 *
 * @author: yc
 * @date: 2021-05-28
 */
public interface TagService extends IService<Tag> {

    /**
     * 新增标签
     *
     * @param tagParam 请求参数
     * @return tagId
     */
    String saveTag(TagParam tagParam);

    /**
     * 更新标签
     *
     * @param tagParam 请求参数
     */
    void updateTag(TagParam tagParam);

    /**
     * 删除标签
     *
     * @param tagParam 请求参数
     */
    void deleteTag(TagParam tagParam);

    /**
     * 标签列表—分页
     *
     * @param page  分页参数
     * @param keyword 关键字
     * @return IPage<TagVO>
     */
    IPage<TagVO> pageTag(Page page, String keyword);

    /**
     * 标签列表—不分页
     *
     * @param keyword 关键字
     * @param tagIds  标签IDS
     * @return List<TagVO>
     */
    List<TagVO> listTag(String keyword, List<String> tagIds);

    /**
     * 核验标签是否匹配
     *
     * @param tagIds 标签IDS
     */
    void checkTagMatch(Set<String> tagIds);
}
