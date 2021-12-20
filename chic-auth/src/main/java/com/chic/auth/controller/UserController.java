package com.chic.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 * @author: gyc
 * @date: 2021-12-13 17:27
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

}
