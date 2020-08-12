package io.pikassa.sdk.helpers

import io.pikassa.sdk.di.component.DaggerRetrofitComponent
import io.pikassa.sdk.entities.BodyRequest
import io.pikassa.sdk.entities.CardDetails
import io.pikassa.sdk.network.PaymentApi
import javax.inject.Inject

/**
Created by pikassa, support@pikassa.io on 26,Июнь,2020
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
        bodyRequest: BodyRequest<*>
    ) = when (bodyRequest.details) {
        is CardDetails -> paymentApi.payByCard(
            uuid,
            apiKey,
            bodyRequest as BodyRequest<CardDetails>
        )
        is Map<*, *> -> paymentApi.payByCustomMethod(
            uuid,
            apiKey,
            bodyRequest as BodyRequest<Map<String, String>>
        )
        else -> {
            throw IllegalArgumentException("unknown detail parameter in bodyRequest")
        }
    }

}