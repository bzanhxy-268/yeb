package com.example.yebserver.config;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.yebserver.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
//                .globalOperationParameters(parameters());
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .version("1.0")
                .description("云E办")
                .title("云E办")
                .build();
    }

//    public List<Parameter> parameters(){
//        ParameterBuilder builder = new ParameterBuilder();
//        ArrayList<Parameter> list = new ArrayList<>();
//        builder.name("token").description("token令牌")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build();
//        list.add(builder.build());
//        return list;
//    }

    public List<ApiKey> securitySchemes(){
        ArrayList<ApiKey> list = new ArrayList<>();
        ApiKey key = new ApiKey("Authorization","Authorization","header");
        list.add(key);
        return list;
    }

    public List<SecurityContext> securityContexts(){
        ArrayList<SecurityContext> list = new ArrayList<>();
        list.add(
                SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/hello/.*"))
                .build()
        );
        return list;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}
