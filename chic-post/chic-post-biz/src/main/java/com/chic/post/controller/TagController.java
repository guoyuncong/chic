package com.chic.post.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chic.core.base.model.vo.R;
import com.chic.core.group.CreateCheck;
import com.chic.core.group.DeleteCheck;
import com.chic.core.group.UpdateCheck;
import com.chic.post.param.TagParam;
import com.chic.post.service.TagService;
import com.chic.post.vo.TagVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签
 *
 * @author: yc
 * @date: 2021-06-01
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("tag")
public class TagController {

    private final TagService tagService;

    /**
     * 新增标签
     *
     * @param tagParam 标签请求参数
     * @return tagId
     */
    @PostMapping("save")
    public R saveTag(@RequestBody @Validated(CreateCheck.class) TagParam tagParam) {
        String tagId = tagService.saveTag(tagParam);
        return R.ofSuccess(tagId);
    }

    /**
     * 更新标签
     *
     * @param tagParam 标签请求参数
     * @return void
     */
    @PostMapping("update")
    public R updateTag(@RequestBody @Validated(UpdateCheck.class) TagParam tagParam) {
        tagService.updateTag(tagParam);
        return R.ofSuccess();
    }

    /**
     * 删除标签
     *
     * @param tagParam 标签请求参数
     * @return void
     */
    @PostMapping("delete")
    public R deleteTag(@RequestBody @Validated(DeleteCheck.class) TagParam tagParam) {
        tagService.deleteTag(tagParam);
        return R.ofSuccess();
    }

    /**
     * 标签列表—分页
     *
     * @param page    分页参数
     * @param keyword 关键字
     * @return IPage<TagVO>
     */
    @GetMapping("page")
    public R pageTag(Page page, String keyword) {
        IPage<TagVO> tagVOPage = tagService.pageTag(page, keyword);
        return R.ofSuccess(tagVOPage);
    }

    /**
     * 标签列表—不分页
     *
     * @param keyword 关键字
     * @return List<TagVO>
     */
    @GetMapping("list")
    public R listTag(String keyword) {
        List<TagVO> tagVOS = tagService.listTag( keyword, null);
        return R.ofSuccess(tagVOS);
    }
}
