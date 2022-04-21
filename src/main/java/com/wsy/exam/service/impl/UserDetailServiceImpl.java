package com.wsy.exam.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wsy.exam.entity.User;
import com.wsy.exam.entity.dto.LoginDTO;
import com.wsy.exam.exception.ServiceException;
import com.wsy.exam.mapper.MenuMapper;
import com.wsy.exam.mapper.mapStruct.LoginMapper;
import com.wsy.exam.service.IRoleService;
import com.wsy.exam.service.IUserRoleService;
import com.wsy.exam.service.IUserService;
import com.wsy.exam.service.VerifyCodeService;
import com.wsy.exam.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @className: com.wsy.exam.service.impl-> UserDetailServiceImpl
 * @description: 自定义UserDetailSerive
 * @author: wsy
 * @createDate: 2022-04-06 12:08
 * @version: 1.0
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Resource
    private MenuMapper menuMapper;

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getById(username);

        if (StringUtils.isNull(user))
        {
            throw new ServiceException("登录用户：" + username + " 不存在");
        }

        LoginDTO loginDTO = LoginMapper.INSTANCE.toDto(user);

        loginDTO.setPermissions(menuMapper.selectPermsByUserId(username));

//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+ userRoleService.getListByUserName(username));
//
//        authorities.add(grantedAuthority);
//
//        loginDTO.setAuthorities(authorities);

        return loginDTO;
    }

}
