package com.example.simplecheckoutservice

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(SimpleCheckoutController::class)
class SimpleCheckoutControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var simpleCheckoutServiceMock: SimpleCheckoutService

    @Test
    fun `should return valid response if request is valid`() {
        val testBody = "[\n" +
                "\"001\",\n" +
                "\"002\",\n" +
                "\"001\",\n" +
                "\"004\",\n" +
                "\"003\"\n" +
                "]"
        val expectedPrice = 360

        every { simpleCheckoutServiceMock.checkout(any()) } returns expectedPrice

        mockMvc.perform(
            MockMvcRequestBuilders.post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content(testBody)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("price").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("price").value(expectedPrice))

        verify(exactly = 1) { simpleCheckoutServiceMock.checkout(any()) }
    }

    @Test
    fun `should convert input data`() {
        val testBody = "[\n" +
                "\"001\",\n" +
                "\"002\",\n" +
                "\"001\",\n" +
                "\"004\",\n" +
                "\"003\"\n" +
                "]"
        val expectedPrice = 360

        every { simpleCheckoutServiceMock.checkout(any()) } returns expectedPrice

        mockMvc.perform(
            MockMvcRequestBuilders.post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testBody)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("price").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("price").value(expectedPrice))

        verify(exactly = 1) { simpleCheckoutServiceMock.checkout(listOf(1L, 2L, 1L, 4L, 3L)) }
    }

    @Test
    fun `should return 400 if request is invalid`() {
        val testBody = "invalid request"

        mockMvc.perform(
            MockMvcRequestBuilders.post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testBody)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

}