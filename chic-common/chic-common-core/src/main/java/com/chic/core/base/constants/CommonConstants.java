package com.chic.core.base.constants;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.io.File;

/**
 * chic 全局常量
 *
 * @author: yc
 * @date: 2021-09-09
 */
public class CommonConstants {

    /**
     * 用户的主目录
     */
    public final static String USER_HOME = System.getProperties().getProperty("user.home");

    /**
     * 系统默认的文件分隔符号
     *      ❶ 在 windows 中 文件文件分隔符 用 \ 或者 / 都可以；
     *      ❷ 在 UNIX 系统上，此字段的值为 /
     */
    public static final String FILE_SEPARATOR = File.separator;

    /**
     * 工作目录：
     *      ❶ Windows：C:\\Users\\username\\
     *      ❷ Linux: ~
     */
    public final static String WORK_DIR = ensureSuffix(USER_HOME, FILE_SEPARATOR) + ".chic" + FILE_SEPARATOR;

    /**
     * 上传文件文件夹
     */
    public final static String UPLOAD_SUB_DIR = "upload/";

    @NonNull
    public static String ensureSuffix(@NonNull String str, @NonNull String suffix) {
        Assert.hasText(str, "String must not be blank");
        Assert.hasText(suffix, "Suffix must not be blank");
        return  str.endsWith(suffix) ?  str.substring(0, str.length() - suffix.length()) : str + suffix;
    }
}
