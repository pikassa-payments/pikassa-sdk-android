package io.pikassa.sample.helpers

import io.pikassa.sample.di.component.DaggerRetrofitComponent
import io.pikassa.sample.entities.*
import io.pikassa.sample.network.OrdersApi
import io.pikassa.sample.utils.getError
import java.lang.Exception
import javax.inject.Inject

/**
Created by Denis Chornyy on 02,Июль,2020
All rights received.
 */
class OrderNetworkHelper {

    companion object {
        const val apiKey = "be4d9881-4af5-4969-bac0-dfe8491a333a"
    }

    @Inject
    lateinit var ordersApi: OrdersApi

    init {
        DaggerRetrofitComponent.builder()
            .build()
            .inject(this)
    }

    /**
     * post order with values
     * @param items items to buy
     */
    suspend fun makeOrder(
        items: List<Item>,
        customerPhone: String,
        customerEmail: String
    ): OrderResponse {
        return try {
            ordersApi.requestOrder(
                apiKey, OrderBody(items, customerPhone, customerEmail)
            )
        } catch (ex: Exception) {
            // if we catch an exception, we'll parse it and return with error
            var parsedErr = ex.getError()
            if (parsedErr == null)
                parsedErr = OrderError("-1", "something went wrong: ${ex.localizedMessage}", null)
            OrderResponse(false, data = null, error = parsedErr)
        }
    }

    /**
     * get invoice status check
     * @param uuid invoice UUID
     */
    suspend fun getStatus(uuid: String): Response<OrderHistoryData, OrderError> {
        return try {
            ordersApi.showOrderHistory(
                apiKey, uuid
            )
        } catch (ex: Exception) {
            // if we catch an exception, we'll parse it and return with error
            var parsedErr = ex.getError()
            if (parsedErr == null)
                parsedErr = OrderError("-1", "something went wrong: ${ex.localizedMessage}", null)
            Response(false, data = null, error = parsedErr)
        }
    }
}