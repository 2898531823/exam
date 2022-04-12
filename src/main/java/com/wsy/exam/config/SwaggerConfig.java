package com.wsy.exam.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @className: com.wsy.exam.config-> SwaggerConfig
 * @description: Swagger接口文档配置类
 * @author: wsy
 * @createDate: 2022-03-30 00:21
 * @version: 1.0
 * @todo:
 */
@EnableSwagger2
@Configuration
@EnableKnife4j
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo()).enable(true)
                .select()
                //apis： 添加swagger接口提取范围
                .apis(RequestHandlerSelectors.basePackage("com.wsy.exam"))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("高校在线考试系统接口文档")
                .description("XX项目描述")
                .contact(new Contact("wsy", "https://www.4399.com", "2898531823@qq.com"))
                .version("1.0")
                .build();
    }

    @Bean
    public Docket webApiUser(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/user/**"))
                .build()
                .groupName("用户")
                .pathMapping("/");
    }

}
