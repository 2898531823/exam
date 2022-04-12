package com.wsy.exam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: com.wsy.exam.controller-> MyConttroller
 * @description: 自定义控制器
 * @author: wsy
 * @createDate: 2022-04-10 14:25
 * @version: 1.0
 */

@Api("自定义控制器")
@RestController
public class MyController {

    @ApiOperation("没有权限提示页面")
    @GetMapping("/unauth")
    public String unauth() {
        return "没有权限访问该页面";
    }
}
