package com.alphaStore.apiGateway.utils.ipmonitor

import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import javax.servlet.http.HttpServletRequest

@Component
object IPResolver {
    fun resolve(request: HttpServletRequest): String {
        request.getHeader("x-forwarded-for")?.let { forwardedFor ->
            return if (forwardedFor.contains("0:0:0:0:0:0:0"))
                "0.0.0.0"
            else
                forwardedFor.split(",")[0]
        } ?: run {
            var ip = request.remoteAddr
                .replace("/", "")
                .split(":")[0]
            if (ip == "0")
                ip = "0.0.0.0"
            return ip
        }
    }

    fun resolve(serverWebExchange: ServerWebExchange): String {
        val headers = serverWebExchange.request.headers
        headers.getFirst("x-forwarded-for")?.let { forwardedFor ->
            return forwardedFor.split(",")[0]
        } ?: run {
            var ip = "${serverWebExchange.request.remoteAddress}"
                .replace("/", "")
                .split(":")[0]
            if (ip == "0")
                ip = "0.0.0.0"
            return ip
        }
    }
}