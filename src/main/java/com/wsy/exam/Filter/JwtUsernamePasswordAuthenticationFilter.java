package com.wsy.exam.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsy.exam.entity.dto.LoginDTO;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @className: com.wsy.exam.Filter-> JwtUsernamePasswordAuthenticationFilter
 * @description: 自定义登录认证过滤器
 * @author: wsy
 * @createDate: 2022-04-11 00:29
 * @version: 1.0
 */
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("认证函数不支持: " + request.getMethod());
        }


        if (request.getContentType() != null) {

            // 判断请求体是否是json数据
            if ((request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    || (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE))) {
                // 用来封装JSON数据
                ObjectMapper mapper = new ObjectMapper();

                UsernamePasswordAuthenticationToken authRequest = null;
                try (ServletInputStream inputStream = request.getInputStream()) {
                    // 从数据流中读取JSON数据并封装到map中
                    LoginDTO authenticationBean = mapper.readValue(inputStream, LoginDTO.class);
                    authRequest = new UsernamePasswordAuthenticationToken(authenticationBean.getUsername(),
                            authenticationBean.getPassword(), authenticationBean.getAuthorities());
                    authRequest.setDetails(authenticationBean);
                } catch (IOException e) {
                    e.printStackTrace();
                    authRequest = new UsernamePasswordAuthenticationToken(
                            "", "");
                }
                return this.getAuthenticationManager().authenticate(authRequest);
            } else {
                return super.attemptAuthentication(request, response);
            }
        }
        return null;

    }
}
