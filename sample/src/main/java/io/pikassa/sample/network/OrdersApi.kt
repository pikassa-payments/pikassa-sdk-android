package io.pikassa.sample.network

import io.pikassa.sample.entities.*
import retrofit2.http.*

/**
Created by pikassa, support@pikassa.io on 02,Июль,2020
All rights received.
 */
interface OrdersApi {

    /**
     * method for requesting the invoice
     * @param apiKey api key in header
     * @param orderBody body of this request
     */
    @POST("orders")
    suspend fun requestOrder(
        @Header("x-api-key") apiKey: String,
        @Body orderBody: OrderBody
    ): OrderResponse

    /**
     * method showing status of invoice
     * @param apiKey api key in header
     * @param uuid invoice UUID
     */
    @GET("orders/{uuid}")
    suspend fun showOrderHistory(
        @Header("x-api-key") apiKey: String,
        @Path("uuid") uuid: String
    ): Response<OrderHistoryData, OrderError>
}