package com.chic.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chic.mybatis.entity.Entity;
import lombok.Data;

/**
 * 日志
 *
 * @author: yc
 * @date: 2021-06-04
 */
@Data
@TableName("log")
public class SysLog extends Entity {

    /**
     * 主键ID
     */
    @TableId(value = "log_id", type = IdType.ASSIGN_UUID)
    private String logId;

    /**
     * 模块名称
     */
    private String module;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 方法名
     */
    private String method;

    /**
     * 请求方式
     */
    private String way;

    /**
     * 请求 uri
     */
    private String uri;

    /**
     * 请求 IP
     */
    private String ip;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 返回结果
     */
    private String result;

    /**
     * 错误信息
     */
    private String errorMsg;
}
