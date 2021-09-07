package com.inventory.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class DocumentationConfig {

    @Bean
    public Docket maaBackOfficeApiDocs() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("inventory-api-v1.0").select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).paths(PathSelectors.regex("/error.*").negate()).build().apiInfo(new ApiInfoBuilder().version("v1").title("Inventory API").description("Inventory API Documentation").build());
    }
}
