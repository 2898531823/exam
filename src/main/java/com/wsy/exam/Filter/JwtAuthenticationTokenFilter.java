package com.wsy.exam.Filter;

import com.alibaba.fastjson.JSONObject;
import com.wsy.exam.entity.dto.LoginDTO;
import com.wsy.exam.service.VerifyCodeService;
import com.wsy.exam.utils.JwtUtil;
import com.wsy.exam.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @className: com.wsy.exam.Filter-> JwtAuthenticationTokenFilter
 * @description: 用户权限处理器
 * @author: wsy
 * @createDate: 2022-04-12 17:14
 * @version: 1.0
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;
    


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String url = request.getRequestURI();


        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String username;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            username = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = "login:" + username;
        JSONObject loginUser = redisCache.getCacheObject(redisKey);


        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录或登陆已过期");
        }

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(loginUser.get("username").toString());
        loginDTO.setPassword(loginUser.get("password").toString());
        loginDTO.setPermissions(loginUser.getJSONArray("permissions").toJavaList(String.class));
        loginDTO.setAuthorities(loginUser.getJSONArray("authorities").toJavaList(GrantedAuthority.class));

        //存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO, null, loginDTO.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}