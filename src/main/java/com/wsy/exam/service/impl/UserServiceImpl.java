package com.wsy.exam.service.impl;

import com.wsy.exam.entity.User;
import com.wsy.exam.entity.dto.LoginDTO;
import com.wsy.exam.mapper.UserMapper;
import com.wsy.exam.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsy.exam.utils.JwtUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wsy
 * @since 2022-04-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public String login(LoginDTO loginDTO) {

        User user = getById(loginDTO.getUsername());



        return null;
    }
}
