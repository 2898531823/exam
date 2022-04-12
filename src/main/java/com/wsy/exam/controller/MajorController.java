package com.wsy.exam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wsy
 * @since 2022-04-04
 */
@Api("专业控制器")
@RestController
@RequestMapping("/admin/major")
public class MajorController {

    @ApiOperation("测试")
    @GetMapping("/hello")
    public String hello(){
        return "asfasf";
    }
}
