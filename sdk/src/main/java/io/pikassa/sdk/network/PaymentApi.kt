package io.pikassa.sdk.network

import io.pikassa.sdk.entities.BodyRequest
import io.pikassa.sdk.entities.CardDetailResponse
import io.pikassa.sdk.entities.CardDetails
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

/**
Created by pikassa, support@pikassa.io on 25,Июнь,2020
All rights received.
 */

/**
 * API for payment requests
 */
interface PaymentApi {
    /**
     * method for payment
     * @param uuid payment identificator
     * @param apiKey key for access to payment
     */
    @PUT("invoices/{uuid}/pay")
    suspend fun payByCustomMethod(
        @Path("uuid") uuid: String,
        @Header("x-api-key") apiKey: String,
        @Body bodyRequest: BodyRequest<Map<String, String>>
    ): CardDetailResponse

    /**
     * method for payment
     * @param uuid payment identificator
     * @param apiKey key for access to payment
     */
    @PUT("invoices/{uuid}/pay")
    suspend fun  payByCard(
        @Path("uuid") uuid: String,
        @Header("x-api-key") apiKey: String,
        @Body bodyRequest: BodyRequest<CardDetails>
    ): CardDetailResponse
}