package com.chic.system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典分类
 *
 * @author: yc
 * @date: 2021-07-02
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("dict")
public class DictController {
}
