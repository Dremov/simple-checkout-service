package com.example.simplecheckoutservice

import org.springframework.stereotype.Service

@Service
class SimpleCheckoutService(val watchRepository: WatchRepository) {
    fun checkout(ids: List<Long>): Int {
        val requestedWatches = watchRepository.getWatches(ids)
        var totalPrice = requestedWatches.sumOf { it?.price ?: 0 }

        totalPrice -= applicableRolexDiscount(ids)
        totalPrice -= applicableKorsDiscount(ids)

        return totalPrice
    }

    private fun applicableRolexDiscount(ids: List<Long>): Int {
        val rolexCount = ids.count { it == 1L }

        return (rolexCount/3)*100
    }

    private fun applicableKorsDiscount(ids: List<Long>): Int {
        val rolexCount = ids.count { it == 2L }

        return (rolexCount/2)*40
    }
}