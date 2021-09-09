package com.chic.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chic.system.entity.Attachment;
import com.chic.system.vo.AttachmentVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 附件
 *
 * @author: yc
 * @date: 2021-07-08
 */

public interface AttachmentService extends IService<Attachment> {

    /**
     * 上传附件
     *
     * @param uploadFile 文件
     */

    void uploadAttachment(MultipartFile uploadFile) throws IOException;


    /**
     * 文件详情
     *
     * @param attachmentId 文件ID
     * @return AttachmentVO
     */
    AttachmentVO detailAttachment(String attachmentId);


    /**
     * 文件分页查询
     *
     * @param page      分页
     * @param keyword   关键字
     * @param mediaType 文件类型
     * @return Page<AttachmentVO>
     */
    Page<AttachmentVO> pageAttachment(Page page, String keyword, String mediaType);

}

