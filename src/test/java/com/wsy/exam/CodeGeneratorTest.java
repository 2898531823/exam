package com.wsy.exam;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * @className: com.wsy.exam-> CodeGeneratorTest
 * @description: 代码自动生成
 * @author: wsy
 * @createDate: 2022-04-03 15:12
 * @version: 1.0
 * @todo:
 */
@SpringBootTest
public class CodeGeneratorTest {

    String projectPath = System.getProperty("user.dir");

    @Test
    void generateCode() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/exam?serverTimezone=GMT%2B8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("wsy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wsy") // 设置父包名
                            .moduleName("exam") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    void generateCode2() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/exam?serverTimezone=GMT%2B8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("wsy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/src/main/java/generator"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wsy") // 设置父包名
                            .moduleName("exam") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/java/generator")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
