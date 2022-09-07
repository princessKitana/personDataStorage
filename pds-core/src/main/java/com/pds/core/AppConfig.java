package com.pds.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class AppConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pds.core.controller"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Person data storage REST API", "Person data storage API to perform CRUD opertations", "1.0", "Terms of service",
                new Contact("Person data storage", "www.pds.com", "pds@pds.com"), "License of API", "API license URL", Collections.emptyList());
    }

}
