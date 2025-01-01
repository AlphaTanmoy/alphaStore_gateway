package com.alphaStore.apiGateway

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/apiGateway")
class HealthCheck {

    @GetMapping("/healthCheck")
    fun healthCheck() : String {
        return "I Am Up"
    }

}