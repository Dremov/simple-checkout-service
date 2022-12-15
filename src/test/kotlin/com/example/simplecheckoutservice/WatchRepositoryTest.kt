package com.example.simplecheckoutservice

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class WatchRepositoryTest {
    lateinit var watchRepository: WatchRepository

    @BeforeEach
    fun setup() {
        watchRepository = WatchRepository()
    }

    @Test
    fun `should return correct amount of watches`() {
        val testIds = listOf(1L, 2L, 1L, 2L)
        val expectedWatches = listOf(
            TestUtils.generateWatch(
                id = 1L,
                price = 100
            ),
            TestUtils.generateWatch(
                id = 2L,
                price = 80
            ),
            TestUtils.generateWatch(
                id = 1L,
                price = 100
            ),
            TestUtils.generateWatch(
                id = 2L,
                price = 80
            )
        )

        val actualListOfWatches = watchRepository.getWatches(testIds)

        expectThat(actualListOfWatches.size).isEqualTo(expectedWatches.size)
    }
}