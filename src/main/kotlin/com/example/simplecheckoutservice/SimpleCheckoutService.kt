package com.example.simplecheckoutservice

import org.springframework.stereotype.Service

@Service
class SimpleCheckoutService(val watchRepository: WatchRepository) {
    fun checkout(ids: List<Long>): Int {
        val requestedWatches = watchRepository.getWatches(ids)
        val totalPrice = requestedWatches.sumOf { it.price }

        return totalPrice
    }
}