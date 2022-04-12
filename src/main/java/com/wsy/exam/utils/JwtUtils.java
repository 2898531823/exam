package com.wsy.exam.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsy.exam.common.R;
import com.wsy.exam.entity.User;
import com.wsy.exam.entity.dto.LoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @className: com.wsy.exam.utils-> JwtUtils
 * @description: Jwt工具类
 * @author: wsy
 * @createDate: 2022-04-05 15:49
 * @version: 1.0
 * @todo:
 */
public class JwtUtils {
    /**
     * 未设置记住我时 token 过期时间
     * */
    private static final long EXPIRATION_TIME = 7200000;

    /**
     * 记住我时 cookie token 过期时间
     * */
    private static final int COOKIE_EXPIRATION_TIME = 1296000;

    private static final String SECRET_KEY = "ThisIsASpringSecurityDemo";
    public static final String COOKIE_TOKEN = "COOKIE-TOKEN";
    public static final String XSRF = "XSRF-TOKEN";

    /**
     * 密钥
     */
    public static final String APP_SECRET = "zjKkye4PN59B2wriTjtVCo3BOYoD1B";

    /**
     * 根据id和昵称获取token
     */
    public static String getJwtToken(LoginDTO loginDTO,StringBuffer stringBuffer,long expirationTime) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // 主题
                .setSubject(loginDTO.getUsername())
                .claim("authorities", stringBuffer)
                // 签发时间
                .setIssuedAt(new Date())
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                // 签名算法
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * 设置登陆成功后令牌返回
     * */
    public static void addAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        // 获取用户登陆角色
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        // 遍历用户角色
        StringBuffer stringBuffer = new StringBuffer();
        authorities.forEach(authority -> {
            stringBuffer.append(authority.getAuthority()).append(",");
        });
        long expirationTime = EXPIRATION_TIME;
        int cookExpirationTime = -1;
        // 处理登陆附加信息
        LoginDTO loginDetails = (LoginDTO) authResult.getPrincipal();
        if (loginDetails.getRememberMe() != null && loginDetails.getRememberMe()) {
            expirationTime = COOKIE_EXPIRATION_TIME * 1000;
            cookExpirationTime = COOKIE_EXPIRATION_TIME;
        }

        String jwt = getJwtToken(loginDetails,stringBuffer,expirationTime);

        Cookie cookie = new Cookie(COOKIE_TOKEN, jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(cookExpirationTime);
        response.addCookie(cookie);

        R resultDetails = R.ok().message("登陆成功").data("Login",loginDetails);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(resultDetails));
        out.flush();
        out.close();
    }

    /**
     * 对请求的验证
     * */
    public static Authentication getAuthentication(HttpServletRequest request) {

        Cookie cookie = WebUtils.getCookie(request, COOKIE_TOKEN);
        String token = cookie != null ? cookie.getValue() : null;

        if (token != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            // 获取用户权限
            Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(claims.get("authorities").toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            String userName = claims.getSubject();
            if (userName != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, null, authorities);
                usernamePasswordAuthenticationToken.setDetails(claims);
                return usernamePasswordAuthenticationToken;
            }
            return null;
        }
        return null;
    }
}
