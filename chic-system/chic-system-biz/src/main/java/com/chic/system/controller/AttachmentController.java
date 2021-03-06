package com.chic.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chic.core.base.model.vo.R;
import com.chic.core.group.CreateCheck;
import com.chic.core.group.DeleteCheck;
import com.chic.core.group.UpdateCheck;
import com.chic.system.param.AttachmentParam;
import com.chic.system.service.AttachmentService;
import com.chic.system.vo.AttachmentVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.IOException;


/**
 * 附件
 *
 * @author: yc
 * @date: 2021-07-07
 */

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("attachment")
public class AttachmentController {

    private final AttachmentService attachmentService;

    /**
     * 上传附件
     *
     * @param uploadFile 文件
     * @return void
     * @throws IOException 异常
     */

    @PostMapping("upload")
    public R uploadAttachment(@RequestParam("file") MultipartFile uploadFile) throws IOException {
        attachmentService.uploadAttachment(uploadFile);
        return R.ofSuccess();
    }

    /**
     * 更新附件
     *
     * @param attachmentParam 请求参数
     * @return void
     */
    @PostMapping("update")
    public R updateAttachment(@RequestBody @Validated(UpdateCheck.class) AttachmentParam attachmentParam) {
        attachmentService.updateAttachment(attachmentParam);
        return R.ofSuccess();
    }

    /**
     * 删除附件
     *
     * @param attachmentParam 请求参数
     * @return void
     */
    @PostMapping("delete")
    public R deleteAttachment(@RequestBody @Validated(DeleteCheck.class) AttachmentParam attachmentParam) {
        attachmentService.deleteAttachment(attachmentParam);
        return R.ofSuccess();
    }


    /**
     * 附件详情
     *
     * @param attachmentId 文件ID
     * @return attachmentVO
     */

    @GetMapping("detail")
    public R detailAttachment(@NotBlank(message = "主键ID不能为空") String attachmentId) {
        AttachmentVO attachmentVO = attachmentService.detailAttachment(attachmentId);
        return R.ofSuccess(attachmentVO);
    }


    /**
     * 附件分页
     *
     * @param page      分页参数
     * @param keyword   关键字
     * @param mediaType 文件类型
     * @return Page<AttachmentVO>
     */

    @GetMapping("page")
    public R pageAttachment(Page page, String keyword, String mediaType) {
        Page<AttachmentVO> attachmentVOPage = attachmentService.pageAttachment(page, keyword, mediaType);
        return R.ofSuccess(attachmentVOPage);
    }

}

