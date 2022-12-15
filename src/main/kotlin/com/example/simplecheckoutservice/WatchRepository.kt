package com.example.simplecheckoutservice

import com.example.simplecheckoutservice.domain.Watch
import org.springframework.stereotype.Component

@Component
class WatchRepository {
    private val allWatches = listOf(
        Watch(id = 1, name = "Rolex", price = 100, discount = "3 for 200"),
        Watch(id = 2, name = "Michael Kors", price = 80, discount = "2 for 120"),
        Watch(id = 3, name = "Swatch", price = 50, discount = ""),
        Watch(id = 4, name = "Casio", price = 30, discount = "")
    )


    fun getWatches(ids: List<Long>): List<Watch?> {

        val watches = mutableListOf<Watch?>()
        ids.forEach { passedId ->
            watches.add(allWatches.find { it.id == passedId})
        }

        return watches
    }
}