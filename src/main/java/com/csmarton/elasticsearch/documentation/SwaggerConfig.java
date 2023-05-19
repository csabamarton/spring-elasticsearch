package com.csmarton.elasticsearch.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("springelasticsearch")
                        .description("elasticsearch-with-spring")
                        .version("1.0")
                        .license(new License()
                                .name("Apache License, Version 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact()
                                .name("Csaba Marton")
                                .url("https://github.com/csabamarton/spring-elasticsearch")
                                .email("csmarton@gmail.com"))
                );
    }
}
