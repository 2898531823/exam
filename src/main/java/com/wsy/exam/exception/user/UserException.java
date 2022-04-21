package com.wsy.exam.exception.user;

import com.wsy.exam.exception.BaseException;

/**
 * @className: com.wsy.exam.exception.user-> UserException
 * @description: 用户信息异常类
 * @author: wsy
 * @createDate: 2022-04-21 13:22
 * @version: 1.0
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
