package com.wsy.exam.exception.user;

/**
 * @className: com.wsy.exam.exception.user-> UserPasswordNotMatchException
 * @description: 用户密码不正确或不符合规范异常类
 * @author: wsy
 * @createDate: 2022-04-21 14:59
 * @version: 1.0
 */
public class UserPasswordNotMatchException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super("user.password.not.match", null);
    }
}
