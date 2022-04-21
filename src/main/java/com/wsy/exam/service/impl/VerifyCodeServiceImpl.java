package com.wsy.exam.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wsy.exam.common.R;
import com.wsy.exam.exception.user.CaptchaException;
import com.wsy.exam.exception.user.CaptchaExpireException;
import com.wsy.exam.service.VerifyCodeService;
import com.wsy.exam.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @className: com.wsy.exam.service.impl-> VerifyCodeServiceImpl
 * @description: 验证码接口实现类
 * @author: wsy
 * @createDate: 2022-04-14 01:57
 * @version: 1.0
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    private RedisCache redisCache;

    private static final long VERIFY_CODE_EXPIRE_TIMEOUT = 60000L;

    // TODO: 2022-4-14
    @Override
    public R send(String email) {
        return null;
    }

    @Override
    public void verify(String key, String code) {

        String cacheObject = redisCache.getCacheObject(key);

        if (cacheObject == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(cacheObject))
        {
            throw new CaptchaException();
        }

        if (StringUtils.isBlank(cacheObject) || !code.toLowerCase().equals(cacheObject.toLowerCase())) {
            throw new RuntimeException("验证码错误");
        }
        redisCache.deleteObject(key);
    }
}
