package com.chic.core.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.io.File;

/**
 * Filename utilities.
 *
 * @author johnniang
 * @date 3/26/19
 */
public class FilenameUtils {

    private FilenameUtils() {
    }

    /**
     * 获取文件名称
     *
     * 如: /home/test/test.txt => fileName: test
     * 如：D:\home\test.txt => fileName: test
     *
     * 注意此处：file.getOriginalFilename() 由于浏览器的不同，得到的结果会有所不同：
     *      结果1：携带盘符
     *      结果2：未携带盘符
     * 需要分情况获取
     *
     * @param originalFilename 文件名
     * @return String
     */
    @NonNull
    public static String getFileName(@NonNull String originalFilename) {
        Assert.hasText(originalFilename, "originalFilename must not be blank");
        // 判断文件名是否携带盘符
        if (originalFilename.contains(File.separator)) {
            int separatorLastIndex = StrUtil.lastIndexOf(originalFilename, File.separator, 0, false);
            originalFilename = originalFilename.substring(separatorLastIndex + 1);
        }
        // 根据 . 分隔
        int dotLastIndex = originalFilename.lastIndexOf(".");
        return originalFilename.substring(0, dotLastIndex);
    }

    /**
     * 获取文件扩展名
     *
     * 注意此处：file.getOriginalFilename() 由于浏览器的不同，得到的结果会有所不同：
     *      结果1：携带盘符
     *      结果2：未携带盘符
     * 需要分情况获取
     *
     * @param originalFilename 文件名
     * @return String
     */
    @NonNull
    public static String getExtension(@NonNull String originalFilename) {
        Assert.hasText(originalFilename, "originalFilename must not be blank");
        // 判断文件名是否携带盘符
        if (originalFilename.contains(File.separator)) {
            int separatorLastIndex = StrUtil.lastIndexOf(originalFilename, File.separator, 0, false);
            originalFilename = originalFilename.substring(separatorLastIndex + 1);
        }
        // Find last dot
        int dotLastIndex = originalFilename.lastIndexOf(".");
        return originalFilename.substring(dotLastIndex + 1);
    }

}
