package com.example.estudo.apigateway.startup.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API RestFul desenvolvida com muita luta mas to muito orgulhoso, por Jonathan Beskow")
                        .version("0.1.1")
                        .description("Api de consulta")
                        .license(new License().name("Apache 2")));
    }

}
