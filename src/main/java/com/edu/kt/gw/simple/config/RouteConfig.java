package com.edu.kt.gw.simple.config;

import com.edu.kt.gw.simple.filter.LoggingFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator msRoute(RouteLocatorBuilder builder) {

        LoggingFilter loggingFilter = new LoggingFilter();

        return builder.routes()
                .route("command-service", r -> r.path("/**")
                        .and().method(HttpMethod.POST)
//                        .and().header("APPNAME","command")
                        .filters(f -> f.addRequestHeader("command", "This is commmand service"))
                        .uri("http://localhost:8081"))
                .route("query-service", r -> r.path("/**")
                        .and().method(HttpMethod.GET)
//                        .and().header("APPNAME","query")
                        .filters(f -> f.filter(loggingFilter.apply(new LoggingFilter.Config())).addRequestHeader("appName", "query"))
                        .uri("http://localhost:8082"))
                .build();
    }

}
