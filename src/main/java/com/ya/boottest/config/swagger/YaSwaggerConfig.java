package com.ya.boottest.config.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>created time:2023/4/25 20:20</p>
 * <p>des:
 *    Swagger 配置文件
 * </p>
 *
 * @author Ya Shi
 */
@Configuration
public class YaSwaggerConfig {

    private License license() {
        return new License()
                .name("MIT")
                .url("https://opensource.org/licenses/MIT");
    }

    private Info info(){
        return new Info()
                .title("Ya API")
                .description("A test project for Mr.Ya.")
                .version("v1.0.0")
                .license(license());
    }
    private ExternalDocumentation externalDocumentation() {
        return new ExternalDocumentation()
                .description("这是一个额外的描述。")
                .url("https://shijizhe.github.io/");
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(info())
                .externalDocs(externalDocumentation());
    }
}
