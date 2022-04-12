package com.wsy.exam.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsy.exam.entity.dto.LoginDTO;
import com.wsy.exam.utils.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className: com.wsy.exam.handler-> MyAuthenticationSuccessHandler
 * @description: 自定义登陆成功处理器
 * @author: wsy
 * @createDate: 2022-04-11 00:43
 * @version: 1.0
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        JwtUtils.addAuthentication(request,response,authentication);

    }
}