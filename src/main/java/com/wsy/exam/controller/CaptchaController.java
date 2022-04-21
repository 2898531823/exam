package com.wsy.exam.controller;

import com.google.code.kaptcha.Producer;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import com.wsy.exam.common.R;
import com.wsy.exam.utils.RedisCache;
import io.swagger.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @className: com.wsy.exam.controller-> AuthController
 * @description: 验证码控制器
 * @author: wsy
 * @createDate: 2022-04-13 00:48
 * @version: 1.0
 */

@Slf4j
@RestController
public class CaptchaController {

    @Autowired
    private Producer producer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisCache redisCache;

    /**
     * 图片验证码
     */
    @GetMapping("/captcha")
    public R captcha() throws IOException {

        String code = producer.createText();
        String uuid = "captchaCode:" +  UUID.randomUUID().toString();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());

        // 存储到redis中
        redisCache.setCacheObject(uuid,code,2, TimeUnit.MINUTES);

        log.info("验证码 -- {} - {}", uuid, code);
        HashMap<String, String> map = new HashMap<>();
        map.put("uuid", uuid);
        map.put("base64Img", base64Img);
        return R.ok().data("code",map);
    }
}
