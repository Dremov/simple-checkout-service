package com.example.simplecheckoutservice

import com.example.simplecheckoutservice.domain.Watch

class TestUtils {
    companion object {
        fun generateWatch(
            id: Long = 0L,
            name: String = "testWatchName",
            price: Int = 0,
            discount: String = "noDiscount"
        ) = Watch(
            id = id,
            name = name,
            price = price,
            discount = discount
        )
    }
}