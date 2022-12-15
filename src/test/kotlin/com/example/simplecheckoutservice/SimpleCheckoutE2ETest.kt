package com.example.simplecheckoutservice

import com.example.simplecheckoutservice.domain.SimpleCheckoutResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimpleCheckoutE2ETest {

    @Autowired
    lateinit var client: WebTestClient

    @Disabled
    @Test
    fun `should return correct result for valid request`() {
        val testRequestBody = listOf(
            "001",
            "002",
            "001",
            "001",
            "004",
            "003")
        val expectedResponse = SimpleCheckoutResponse(360)

        client.post()
            .uri {
                it.path("/checkout")
                it.build()
            }
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(testRequestBody)
            .exchange()
            .expectStatus().isOk
            .expectBody().json(ObjectMapper().writeValueAsString(expectedResponse))
    }
}