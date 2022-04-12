package com.wsy.exam.config;

import com.wsy.exam.Filter.JwtAuthenticationFilter;
import com.wsy.exam.Filter.JwtUsernamePasswordAuthenticationFilter;
import com.wsy.exam.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @className: com.wsy.exam.config-> SecurityConfig
 * @description: spring security配置类
 * @author: wsy
 * @createDate: 2022-04-05 12:31
 * @version: 1.0
 * @todo:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static String ADMIN = "ROLE_ADMIN";

    public static String USER = "ROLE_USER";

    @Autowired
    private UserDetailsService userDetailsService;


    //放行白名单
    private final static String[] PERMIT_ALL_MAPPING = {

    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().loginProcessingUrl("/user/login").permitAll()
                .and().authorizeRequests()
                .antMatchers("/admin/**","/doc.html").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/unauth")
                .and().addFilterBefore(new JwtAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .cors().configurationSource(corsConfigurationSource())
                .and().csrf().disable();

        http.addFilterAt(jwtUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        //禁用Session
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        /*return new BCryptPasswordEncoder();*/
        return NoOpPasswordEncoder.getInstance();
    }

    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter() throws Exception {
        JwtUsernamePasswordAuthenticationFilter filter = new JwtUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        //filter.setAuthenticationFailureHandler(new FailureHandler());
        filter.setFilterProcessesUrl("/user/login");

        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        //所有的请求都允许跨域
        source.registerCorsConfiguration("/**",corsConfiguration);
        return  source;
    }

}
