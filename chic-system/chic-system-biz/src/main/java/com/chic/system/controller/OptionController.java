package com.chic.system.controller;

import com.chic.core.base.model.vo.R;
import com.chic.system.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.List;

/**
 * 配置项
 *
 * @author: yc
 * @date: 2021-09-10
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("setting")
public class OptionController {

    private final OptionService optionService;

    /**
     * 新增配置项
     *
     * @param optionParam 配置项 map
     * @return void
     */
    @PostMapping("save")
    public R saveOptions(@RequestBody @NotEmpty Map<String, Object> optionParam) {
        optionService.saveOptions(optionParam);
        return R.ofSuccess();
    }

    /**
     * 获取所有配置项
     *
     * @param keys 配置项 key
     * @return Map<String, String>
     */
    @GetMapping("list")
    public R listOption(List<String> keys) {
        Map<String, String> optionMap = optionService.listOption(keys);
        return R.ofSuccess(optionMap);
    }
}
