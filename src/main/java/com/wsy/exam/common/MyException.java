package com.wsy.exam.common;

/**
 * @className: com.wsy.exam.common-> MyException
 * @description: 统一异常处理类
 * @author: wsy
 * @createDate: 2022-04-11 00:17
 * @version: 1.0
 */
public class MyException extends RuntimeException {

    private int code;
    private String msg;

    public MyException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}