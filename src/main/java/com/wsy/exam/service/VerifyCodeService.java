package com.wsy.exam.service;

import com.wsy.exam.common.R;

/**
 * @className: com.wsy.exam.service-> VerifyCodeService
 * @description: 验证码服务
 * @author: wsy
 * @createDate: 2022-04-14 01:51
 * @version: 1.0
 */
public interface VerifyCodeService {

    public R send(String email);

    public void verify(String key, String code);

}
