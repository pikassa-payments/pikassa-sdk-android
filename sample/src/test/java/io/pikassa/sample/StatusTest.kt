package io.pikassa.sample

import io.pikassa.sample.helpers.OrderNetworkHelper
import io.pikassa.sample.repository.OrdersRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
Created by Denis Chornyy on 02,Июль,2020
All rights received.
 */
class StatusTest {
    @Test
    fun getHistory() {
        runBlocking {
            val repo = OrdersRepository(OrderNetworkHelper())
            val testUuid = "65b7bf5c-736f-490c-a303-080d3b26a9fe"
            val response = repo.getStatus(testUuid)
            response.toString()
        }
    }
}