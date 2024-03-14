package com.ya.boottest.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
/**
 * <p>created time:2023/4/25 19:34</p>
 * <p>des:
 *     代码生成器（新）
 * </p>
 *
 * @author Ya Shi
 */
public class YaCodeGenerator {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/study", "root", "123456")
            .dbQuery(new MySqlQuery());

    /**
     * 输出路径
     */
    private static final String outputDir = System.getProperty("user.dir") + "/src/main/java";

    public static void main(String[] args) {

        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> {
                    builder.author("Ya Shi") // 设置作者
                            .enableSpringdoc()
                            // .enableSwagger() // 开启 swagger 模式
                            .outputDir(outputDir)   // 指定输出目录
                            .disableOpenDir();   //禁止打开输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ya.boottest.autocode.order"); // 设置父包名
                })
                .strategyConfig(builder -> builder.addInclude("test_gender") // 设置需要生成的表名
                        .serviceBuilder().enableFileOverride()
                        .entityBuilder().enableFileOverride().enableLombok()
                        .mapperBuilder().enableFileOverride())
                .templateConfig(builder -> builder
                        .disable(TemplateType.CONTROLLER)
                        .disable(TemplateType.XML))
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
