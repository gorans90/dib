package com.dib.demo.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String CONTROLLER_PACKAGE = "com.dib.demo.controller";
    private static final String AUTHORIZATION = "Authorization";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(CONTROLLER_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .enableUrlTemplating(false)
                .pathMapping("/")
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .securitySchemes(Lists.newArrayList(securitySchema()));
    }

    private ApiKey securitySchema() {
        return new ApiKey(AUTHORIZATION, AUTHORIZATION, "header");
    }

}
