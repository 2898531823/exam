package com.wsy.exam.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsy.exam.common.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className: com.wsy.exam.handler-> UserAuthAccessDeniedHandler
 * @description: 暂无权限处理类
 * @author: wsy
 * @createDate: 2022-04-14 10:35
 * @version: 1.0
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    /**
     * 暂无权限返回结果
     * @author zwq
     * @date 2020/4/4
     * @param request
     * @param response
     * @param exception
     * @return
     **/
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception){

        try {
            R r = R.error().message("权限不足").code(403);
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(r));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

