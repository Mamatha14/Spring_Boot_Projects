package com.example.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("customer-application", rt -> rt.path("/customers/**")
                        .uri("http://localhost:8083/"))
                .route("account-application", rt -> rt.path("/accounts/**")
                        .uri("http://localhost:8082/"))
                .build();

    }
}
