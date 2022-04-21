package com.wsy.exam.handler;

import com.wsy.exam.common.R;
import com.wsy.exam.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: com.wsy.exam.handler-> UserAuthenticationEntryPointHandler
 * @description: 用户未登录处理类
 * @author: wsy
 * @createDate: 2022-04-14 10:42
 * @version: 1.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        R result = R.error().message("认证失败请重新登录").code(HttpStatus.UNAUTHORIZED.value());

        WebUtils.write(response,result);

    }
}
