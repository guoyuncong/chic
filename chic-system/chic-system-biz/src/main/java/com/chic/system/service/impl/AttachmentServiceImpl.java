package com.chic.system.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chic.core.base.constants.CommonConstants;
import com.chic.core.base.constants.SymbolConstants;
import com.chic.core.util.FilenameUtils;
import com.chic.mybatis.util.PageConvertUtil;
import com.chic.system.enmus.AttachmentType;
import com.chic.system.convert.AttachmentConvert;
import com.chic.system.entity.Attachment;
import com.chic.system.mapper.AttachmentMapper;
import com.chic.system.service.AttachmentService;
import com.chic.system.service.OptionService;
import com.chic.system.vo.AttachmentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * 附件
 *
 * @author: yc
 * @date: 2021-07-08
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {

    private final OptionService optionService;

    @Override
    public void uploadAttachment(MultipartFile uploadFile) throws IOException {
        Assert.notNull(uploadFile, "Multipart file must not be null");
        // 获取当前年月
        int year = DateUtil.thisYear();
        int month = DateUtil.thisMonth() + 1;
        String monthString = month < 10 ? "0" + month : String.valueOf(month);
        // 构建文件目录
        String subDir = CommonConstants.UPLOAD_SUB_DIR + year + CommonConstants.FILE_SEPARATOR + monthString + CommonConstants.FILE_SEPARATOR;
        // 由于浏览器的不同，此处可能获取到携带盘符或者未携带盘符
        String originalFilename = uploadFile.getOriginalFilename();
        // 获取文件名
        String fileName = FilenameUtils.getFileName(originalFilename);
        // 获取文件扩展名
        String extension = FilenameUtils.getExtension(originalFilename);
        // 构建文件地址【更改文件名，以便可以上传同名文件】
        String subFilePath = StrUtil.concat(true, subDir, fileName, "-", IdUtil.simpleUUID(), ".", extension);
        // 上传文件，如果父级文件夹目录不存在，则新建文件夹
        File file = new File(CommonConstants.WORK_DIR + subFilePath);
        if (! file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        uploadFile.transferTo(file);
        // 以图片读取文件
        InputStream uploadFileInputStream = new FileInputStream(file);
        BufferedImage bufferedImage = ImageIO.read(uploadFileInputStream);
        // 构建附件
        Attachment attachment = new Attachment();
        attachment.setAttachmentName(fileName);
        attachment.setPath(subFilePath);
        attachment.setMediaType(MediaType.valueOf(Objects.requireNonNull(uploadFile.getContentType())).toString());
        attachment.setSize(uploadFile.getSize());
        attachment.setHeight(bufferedImage.getHeight());
        attachment.setWidth(bufferedImage.getWidth());
        this.baseMapper.insert(attachment);
    }

    @Override
    public AttachmentVO detailAttachment(String attachmentId) {
        Attachment attachment = this.baseMapper.selectById(attachmentId);
        return AttachmentConvert.INSTANCE.convert2attachmentVO(attachment);
    }

    @Override
    public Page<AttachmentVO> pageAttachment(Page page, String keyword, String mediaType) {
        LambdaQueryWrapper<Attachment> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StrUtil.isNotEmpty(mediaType), Attachment::getMediaType, mediaType);
        queryWrapper.like(StrUtil.isNotEmpty(keyword), Attachment::getAttachmentName, keyword);
        Page<Attachment> attachmentPage = this.baseMapper.selectPage(page, queryWrapper);
        List<AttachmentVO> attachmentVOS = AttachmentConvert.INSTANCE.convert2attachmentVOS(attachmentPage.getRecords());
        // 如果附件类型为本地附件类型，需要拼接服务地址
        String blogBaseUrl = optionService.getBlogBaseUrl();
        attachmentVOS.forEach(attachmentVO -> {
            // 本地服务器路径，需要拼接 BLOG_URL:PORT
            if (Objects.equals(attachmentVO.getAttachmentType(), AttachmentType.LOCAL)) {
                String path = StrUtil.concat(Boolean.TRUE, blogBaseUrl, SymbolConstants.LEFT_FORWARD_SLASH, attachmentVO.getPath());
                attachmentVO.setPath(path);
            }
        });
        return PageConvertUtil.convert(attachmentPage, attachmentVOS);
    }
}

