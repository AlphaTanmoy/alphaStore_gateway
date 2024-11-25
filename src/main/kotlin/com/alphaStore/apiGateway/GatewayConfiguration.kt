package com.alphaStore.apiGateway

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder

@Configuration
class GatewayConfiguration {

    @Bean
    fun routes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()

            .route("cart") { r ->
                r.path("/cart/**")
                    .uri("http://localhost:8083")
            }

            .route("merchant") { r ->
                r.path("/merchant/**")
                    .uri("http://localhost:8084")
            }

            .route("product") { r ->
                r.path("/product/**")
                    .uri("http://localhost:8085")
            }

            .route("seeder") { r ->
                r.path("/seeder/**")
                    .uri("http://localhost:8086")
            }

            .route("user") { r ->
                r.path("/user/**")
                    .uri("http://localhost:8087")
            }

            .route("wallet") { r ->
                r.path("/wallet/**")
                    .uri("http://localhost:8088")
            }
            .build()
    }
}