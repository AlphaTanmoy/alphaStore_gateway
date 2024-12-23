package com.alphaStore.apiGateway

import com.alphaStore.apiGateway.utils.KeywordsAndConstants
import com.alphaStore.apiGateway.utils.ipmonitor.IPResolver
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class RequestedIpFilter : GatewayFilter {
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val ip = IPResolver.resolve(exchange)
        return chain.filter(
            exchange
                .mutate()
                .request(
                    exchange
                        .request
                        .mutate()
                        .header(
                            KeywordsAndConstants.HEADER_REQUESTING_IP,
                            ip
                        )
                        .build()
                )
                .build()
        )
    }
}