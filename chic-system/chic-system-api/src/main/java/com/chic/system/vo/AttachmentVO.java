package com.chic.system.vo;

import com.chic.system.enmus.AttachmentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 附件
 *
 * @author: yc
 * @date: 2021-07-08
 */
@Data
public class AttachmentVO {

    /**
     * 主键ID
     */
    private String attachmentId;

    /**
     * 附件名称
     */
    private String attachmentName;

    /**
     * 附件分类
     */
    private AttachmentType attachmentType;

    /**
     * 文件类型
     */
    private String mediaType;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 高度
     */
    private Integer height;

    /**
     * 宽度
     */
    private Integer width;

    /**
     * 上传日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
}
