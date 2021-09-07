package com.chic.post.param;

import com.chic.core.group.CreateCheck;
import com.chic.core.group.DeleteCheck;
import com.chic.core.group.UpdateCheck;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * 标签【新增|修改|删除】请求参数
 *
 * @author: yc
 * @date: 2021-06-01
 */
@Data
public class TagParam {

    /**
     * 标签ID
     */
    @NotBlank(message = "别名不能为空", groups = {UpdateCheck.class})
    private String tagId;

    /**
     * 标签IDS
     */
    @Size(min = 1, max = 10, groups = DeleteCheck.class, message = "一次性最多允许删除 10 个")
    private Set<String> tagIds;

    /**
     * 标签名称
     */
    @NotBlank(message = "别名不能为空", groups = {CreateCheck.class, UpdateCheck.class})
    private String tagName;

    /**
     * 别名，简称
     * 建议英文填写
     */

    private String abbr;
}
