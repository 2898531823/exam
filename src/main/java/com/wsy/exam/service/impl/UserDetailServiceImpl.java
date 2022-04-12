package com.wsy.exam.service.impl;

import com.wsy.exam.entity.User;
import com.wsy.exam.entity.dto.LoginDTO;
import com.wsy.exam.mapper.mapStruct.LoginMapper;
import com.wsy.exam.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getById(username);

        if (user == null) {
            throw new RuntimeException("用户不存在!");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+user.getRole());

        authorities.add(grantedAuthority);
        LoginDTO loginDTO = LoginMapper.INSTANCE.toDto(user);
        loginDTO.setAuthorities(authorities);

        return loginDTO;
    }

}
