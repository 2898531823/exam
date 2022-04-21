package com.wsy.exam.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsy.exam.common.R;
import com.wsy.exam.entity.User;
import com.wsy.exam.entity.dto.LoginDTO;
import com.wsy.exam.exception.ServiceException;
import com.wsy.exam.exception.user.UserPasswordNotMatchException;
import com.wsy.exam.mapper.UserMapper;
import com.wsy.exam.service.IUserService;
import com.wsy.exam.service.VerifyCodeService;
import com.wsy.exam.utils.JwtUtil;
import com.wsy.exam.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wsy
 * @since 2022-04-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Override
    public R login(LoginDTO loginDTO) {

        if (loginDTO.getUuid() == null || loginDTO.getCode() == null) {
            throw new RuntimeException("验证码不能为空");
        }
        verifyCodeService.verify(loginDTO.getUuid(), loginDTO.getCode());

        Authentication authenticate = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

            authenticate = authenticationManager.authenticate(authenticationToken);

        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException();
            } else {
                throw new ServiceException(e.getMessage());
            }
        }

        //使用userid生成token
        LoginDTO user = (LoginDTO) authenticate.getPrincipal();

        String username = user.getUsername();
        String jwt = JwtUtil.createJWT(username);

        //authenticate存入redis
        redisCache.setCacheObject("login:" + username, user);

        //把token响应给前端
        return R.ok().message("登陆成功").data("token", jwt);
    }


    @Override
    public R logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginUser = (LoginDTO) authentication.getPrincipal();
        String username = loginUser.getUsername();
        redisCache.deleteObject("login:" + username);
        return R.ok().message("注销成功");
    }
}
