package com.wsy.exam;


import com.alibaba.fastjson.JSON;
import com.wsy.exam.mapper.MenuMapper;
import com.wsy.exam.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ExamApplicationTests {

    @Resource
    private MenuMapper menuMapper;


    @Test
    void contextLoads() {
        List<String> admin = menuMapper.selectPermsByUserId("admin");
        System.out.println("admin = " + admin);


    }

}




