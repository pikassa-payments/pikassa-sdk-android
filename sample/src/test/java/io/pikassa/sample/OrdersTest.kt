package io.pikassa.sample

import io.pikassa.sample.entities.Item
import io.pikassa.sample.helpers.OrderNetworkHelper
import io.pikassa.sample.repository.OrdersRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
Created by Denis Chornyy on 02,Июль,2020
All rights received.
 */
class OrdersTest {
    @Test
    fun testOrder() {
        val ordersRepository = OrdersRepository(OrderNetworkHelper())

        val testItems = listOf(Item(1, 2), Item(2, 6))
        val testEmail = "test@test.ru"
        val testPhone = "88005553535"
        runBlocking {
            val response = ordersRepository.makeOrder(testItems, testPhone, testEmail)
            println(response.toString())
        }
    }
}