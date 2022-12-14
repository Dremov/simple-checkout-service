package com.example.simplecheckoutservice

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SimpleCheckoutServiceTest {

    private lateinit var simpleCheckoutService: SimpleCheckoutService

    @MockK
    lateinit var watchRepositoryMock: WatchRepository

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        simpleCheckoutService = SimpleCheckoutService(watchRepositoryMock)
    }

    @Test
    fun `should call repo`() {
        val testWatches = listOf(
            TestUtils.generateWatch(
                id = 1L,
                price = 1
            ),
            TestUtils.generateWatch(
                id = 2L,
                price = 2
            ),
            TestUtils.generateWatch(
                id = 3L,
                price = 3
            )
        )

        every { watchRepositoryMock.getWatches(any()) } returns testWatches

        simpleCheckoutService.checkout(emptyList())

        verify { watchRepositoryMock.getWatches(any()) }
    }

    @Test
    fun `should return correct price without discount`() {
        val testIds = listOf(1L, 2L, 3L)
        val testWatches = listOf(
            TestUtils.generateWatch(
                id = 1L,
                price = 1
            ),
            TestUtils.generateWatch(
                id = 2L,
                price = 2
            ),
            TestUtils.generateWatch(
                id = 3L,
                price = 3
            )
        )
        val expectedPrice = 6

        every { watchRepositoryMock.getWatches(any()) } returns testWatches

        val actualPrice = simpleCheckoutService.checkout(testIds)

        expectThat(actualPrice).isEqualTo(expectedPrice)
    }
}