package com.wsy.exam.exception.user;

/**
 * @className: com.wsy.exam.exception.user-> CaptchaException
 * @description: 验证码错误异常类
 * @author: wsy
 * @createDate: 2022-04-21 13:21
 * @version: 1.0
 */
public class CaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
