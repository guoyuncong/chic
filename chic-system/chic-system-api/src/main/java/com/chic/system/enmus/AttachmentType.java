package com.chic.system.enmus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 附件类型
 *
 * @author yc
 * @date 2021-09-10
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AttachmentType {

    /**
     * 服务器
     */
    LOCAL,

    ;

}
