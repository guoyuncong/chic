package com.chic.post.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chic.core.base.model.vo.R;
import com.chic.core.group.CreateCheck;
import com.chic.core.group.DeleteCheck;
import com.chic.core.group.UpdateCheck;
import com.chic.post.param.PostEditParam;
import com.chic.post.param.PostParam;
import com.chic.post.param.PostUpdateStatusParam;
import com.chic.post.service.PostService;
import com.chic.post.vo.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * 文章
 *
 * @author: yc
 * @date: 2021-05-27
 */
@Validated
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 新增文章
     *
     * @param postParam 文章请求参数
     * @return postId
     */
    @PostMapping("save")
    public R savePost(@RequestBody @Validated(CreateCheck.class) PostParam postParam) {
        String postId = postService.savePost(postParam);
        return R.ofSuccess(postId);
    }

    /**
     * 设置文章属性
     *
     * @param postParam 文章请求参数
     * @return void
     */
    @PostMapping("set")
    public R setPost(@RequestBody @Validated(UpdateCheck.class) PostParam postParam) {
        postService.setPost(postParam);
        return R.ofSuccess();
    }

    /**
     * 编辑文章内容
     *
     * @param postEditParam 文章请求参数
     * @return void
     */
    @PostMapping("edit")
    public R editPost(@RequestBody PostEditParam postEditParam) {
        postService.editPost(postEditParam);
        return R.ofSuccess();
    }

    /**
     * 更新状态
     *
     * @param param 文章请求参数
     * @return void
     */
    @PostMapping("updateStatus")
    public R updateStatus(@RequestBody PostUpdateStatusParam param) {
        postService.updateStatus(param);
        return R.ofSuccess();
    }

    /**
     * 删除文章
     *
     * @param postParam 文章请求参数
     * @return void
     */
    @PostMapping("delete")
    public R deletePost(@RequestBody @Validated(DeleteCheck.class) PostParam postParam) {
        postService.deletePost(postParam);
        return R.ofSuccess();
    }

    /**
     * 文章详情
     *
     * @param postId 文章ID
     * @return PostVO
     */
    @GetMapping("detail")
    public R detailPost(String postId) {
        PostVO postVO = postService.detailPost(postId);
        return R.ofSuccess(postVO);
    }

    /**
     * 文章详情【客户端访问】
     *
     * @param abbr 文章别名
     * @return PostVO
     */
    @GetMapping("client/detail")
    public R clientDetailPost(@NotBlank String abbr) {
        PostVO postVO = postService.clientDetailPost(abbr);
        return R.ofSuccess(postVO);
    }

    /**
     * 文章列表——分页
     *
     * @param page        分页参数
     * @param status      文章状态
     * @param keyword     关键字
     * @param categoryIds 分类ID
     * @param categoryIds 分类ID
     * @return Page<PostVO>
     */
    @GetMapping("page")
    public R pagePost(Page page,
                      Integer status,
                      String keyword,
                      String categoryIds,
                      String tagIds) {
        Page<PostVO> postVOPage = postService.pagePost(page, keyword, status, categoryIds, tagIds);
        return R.ofSuccess(postVOPage);
    }
}
