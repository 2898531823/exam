package com.wsy.exam.exception.user;

/**
 * @className: com.wsy.exam.exception.user-> CaptchaExpireException
 * @description: 验证码失效异常类
 * @author: wsy
 * @createDate: 2022-04-21 13:38
 * @version: 1.0
 */
public class CaptchaExpireException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException()
    {
        super("user.jcaptcha.expire", null);
    }
}