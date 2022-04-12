package com.wsy.exam;


import com.wsy.exam.entity.User;
import com.wsy.exam.entity.dto.LoginDTO;
import com.wsy.exam.mapper.mapStruct.LoginMapper;
import com.wsy.exam.service.IUserService;
import com.wsy.exam.service.impl.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ExamApplicationTests {

    @Autowired
    private IUserService userService;
    

    @Test
    void contextLoads() {

        User byId = userService.getById("1851300433");
        LoginDTO loginDTO = LoginMapper.INSTANCE.toDto(byId);
        System.out.println("loginDTO = " + loginDTO);

    }

}
