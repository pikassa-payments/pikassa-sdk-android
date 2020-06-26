package io.pikassa.sdk.helpers

import io.pikassa.sdk.entities.BodyRequest
import io.pikassa.sdk.entities.CardDetails
import io.pikassa.sdk.entities.PaymentMethod
import io.pikassa.sdk.network.PaymentApi

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */

/**
 * Class that helps to achieve requests
 */
class PaymentHelper(private val paymentApi: PaymentApi) {
    suspend fun requestPayment(
        uuid: String,
        apiKey: String,
        bodyRequest: BodyRequest
    ) = paymentApi.payByCard(uuid, apiKey, bodyRequest)
}