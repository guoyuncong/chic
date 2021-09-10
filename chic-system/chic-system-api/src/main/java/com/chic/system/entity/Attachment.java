package com.chic.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chic.mybatis.entity.Entity;
import com.chic.system.enmus.AttachmentType;
import lombok.Data;

/**
 * 附件
 *
 * @author: yc
 * @date: 2021-07-08
 */
@Data
@TableName("attachment")
public class Attachment extends Entity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
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
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;
}
