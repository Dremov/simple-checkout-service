package com.example.simplecheckoutservice

import com.example.simplecheckoutservice.domain.SimpleCheckoutResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleCheckoutController(val simpleCheckoutService: SimpleCheckoutService) {


    @PostMapping("/checkout")
    fun checkout(@RequestBody requestBody: Array<String>): SimpleCheckoutResponse {
        val price = simpleCheckoutService.checkout()

        return SimpleCheckoutResponse(price)
    }
}