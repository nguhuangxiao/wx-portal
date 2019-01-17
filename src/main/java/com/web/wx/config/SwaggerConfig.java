package com.web.wx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Profile("dev")
@Configuration
public class SwaggerConfig {

    @Value("${enableSwagger}")
    private boolean enableSwagger;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.web.wx")) //api接口包扫描路径
                .paths(PathSelectors.any())
                .build()
                .enable(enableSwagger);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot-饿了么APP")
                .description("")
                .version("1.0")
                .build();
    }

}
