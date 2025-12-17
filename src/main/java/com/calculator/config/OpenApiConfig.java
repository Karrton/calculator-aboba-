package com.calculator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация OpenAPI/Swagger документации.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Настройка OpenAPI документации.
     */
    @Bean
    public OpenAPI calculatorOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Calculator API")
                .description("REST API для веб-калькулятора с расширенными возможностями")
                .version("1.0.0")
                .contact(new Contact()
                    .name("РИС-22-1б Team")
                    .email("team@calculator.com")));
    }
}
