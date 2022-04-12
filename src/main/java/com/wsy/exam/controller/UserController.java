package com.wsy.exam.controller;

import com.wsy.exam.common.R;
import com.wsy.exam.entity.dto.LoginDTO;
import com.wsy.exam.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wsy
 * @since 2022-04-08
 */
@Api(tags = "User Controller")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @ApiOperation("登录")
    @ApiImplicitParam(name = "loginDTO",value = "用户登陆所需字段")
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO)
    {

        System.out.println("收到请求");
        return userService.login(loginDTO);
    }

    @GetMapping("test")
    String test()
    {
        System.out.println("ssssssssssss");
        return "hello";
    }
}
