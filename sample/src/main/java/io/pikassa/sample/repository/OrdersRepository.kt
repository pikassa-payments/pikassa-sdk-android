package io.pikassa.sample.repository

import io.pikassa.sample.entities.Item
import io.pikassa.sample.helpers.OrderNetworkHelper

/**
Created by Denis Chornyy on 02,Июль,2020
All rights received.
 */
class OrdersRepository(private val ordersHelper: OrderNetworkHelper) {
    suspend fun makeOrder(items: List<Item>, customerPhone: String, customerEmail: String) =
        ordersHelper.makeOrder(items, customerPhone, customerEmail)

    suspend fun getStatus(uuid: String) = ordersHelper.getStatus(uuid)
}