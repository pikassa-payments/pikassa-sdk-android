package io.pikassa.sdk.repositories

import io.pikassa.sdk.entities.BodyRequest
import io.pikassa.sdk.helpers.PaymentHelper

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */

/**
 * Repository class for requesting the payment
 */
class PaymentRepository(private val paymentHelper: PaymentHelper) {
    suspend fun requestPayment(
        uuid: String,
        apiKey: String,
        bodyRequest: BodyRequest
    ) =
        paymentHelper.requestPayment(uuid, apiKey, bodyRequest)
}