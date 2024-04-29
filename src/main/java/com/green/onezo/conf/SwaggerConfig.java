package com.green.onezo.conf;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info( title = "주문결제 시스템 ",
                description = "주문결제 시스템",

        info = @Info(
                title = "Onezo API Documentation",
                description = "OnezoChicken: 장바구니, 회원, 메뉴, 결제, 주문내역, 매장",
                version = "v1.0.0"
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi groupedOpenApi(){
        String[] paths = {"/**"}; //

        return GroupedOpenApi.builder()
                .group("모든 Controller")
                .pathsToMatch(paths)
                .build();
    }



}