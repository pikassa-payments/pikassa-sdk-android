package io.pikassa.sdk

import io.pikassa.sdk.entities.*
import io.pikassa.sdk.helpers.PaymentHelper
import io.pikassa.sdk.repositories.PaymentRepository
import kotlinx.coroutines.runBlocking

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */
/**
 * Main Class to use for payment
 */
object Pikassa {

    // variable for apiKey
    private lateinit var apiKey: String

    // working with payments
    private lateinit var paymentRepository: PaymentRepository

    fun init(apiKey: String) {
        this.apiKey = apiKey
        paymentRepository = PaymentRepository(PaymentHelper())
    }

    fun sendCardData(
        uuid: String,
        requestId: String,
        paymentMethod: PaymentMethod = PaymentMethod.BANK_CARD,
        details: CardDetails,
        onSuccess: (RedirectResponse) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        val body = BodyRequest(requestId, paymentMethod, details)
        runBlocking {
            val response =
                paymentRepository.requestPayment(uuid, apiKey, body)
            // Todo: Проверить, можно ли их сделать не null
            if(response.success) {
                response.data?.let { it.redirect?.let { it1 -> onSuccess(it1) } }
            }
            else {
                response.error?.let { onError(it) }
            }
        }
    }
}