package com.wsy.exam.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsy.exam.common.R;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className: com.wsy.exam.utils-> WebUtils
 * @description:
 * @author: wsy
 * @createDate: 2022-04-17 01:49
 * @version: 1.0
 */
public class WebUtils
{
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param R 待渲染的数据
     */
    public static void write(HttpServletResponse response, R result) {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(result));
            writer.flush();
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}