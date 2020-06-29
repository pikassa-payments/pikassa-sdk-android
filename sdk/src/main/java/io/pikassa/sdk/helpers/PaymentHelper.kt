package io.pikassa.sdk.helpers

import io.pikassa.sdk.di.component.DaggerRetrofitComponent
import io.pikassa.sdk.entities.BodyRequest
import io.pikassa.sdk.network.PaymentApi
import javax.inject.Inject

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */

/**
 * Class that helps to achieve requests
 */
class PaymentHelper {

    @Inject
    lateinit var paymentApi: PaymentApi

    init {
        DaggerRetrofitComponent.builder()
            .build()
            .inject(this)
    }

    suspend fun requestPayment(
        uuid: String,
        apiKey: String,
        bodyRequest: BodyRequest
    ) = paymentApi.payByCard(uuid, apiKey, bodyRequest)
}