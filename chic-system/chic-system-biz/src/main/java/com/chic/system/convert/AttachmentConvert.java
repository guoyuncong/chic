/*
package com.chic.system.convert;

import com.chic.system.entity.Attachment;
import com.chic.system.vo.AttachmentVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

*/
/**
 * 附件映射类
 *
 * @author: yc
 * @date: 2021-07-08
 *//*

@Mapper
public interface AttachmentConvert {

    AttachmentConvert INSTANCE = Mappers.getMapper(AttachmentConvert.class);

    */
/**
     * entity -> vo
     *
     * @param attachment entity
     * @return vo
     *//*

    AttachmentVO convert2attachmentVO(Attachment attachment);

    */
/**
     * list<entity> -> list<vo>
     *
     * @param attachments list<entity>
     * @return list<vo>
     *//*

    List<AttachmentVO> convert2attachmentVOS(List<Attachment> attachments);
}
*/
