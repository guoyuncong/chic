package com.chic.system.param;

import com.chic.core.group.DeleteCheck;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 附件请求参数
 *
 * @author: yc
 * @date: 2021-09-16
 */
@Data
public class AttachmentParam {

    /**
     * 附件ID
     */
    @NotBlank(message = "附件ID不能为空", groups = DeleteCheck.class)
    private String attachmentId;
}
