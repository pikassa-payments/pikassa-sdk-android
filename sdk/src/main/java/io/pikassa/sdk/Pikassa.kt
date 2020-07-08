package io.pikassa.sdk

import io.pikassa.sdk.entities.*
import io.pikassa.sdk.ext.getApiError
import io.pikassa.sdk.helpers.PaymentHelper
import io.pikassa.sdk.repositories.PaymentRepository
import kotlinx.coroutines.*

/**
Created by pikassa, support@pikassa.io on 26,Июнь,2020
All rights received.
 */
/**
 * Main Class to use for payment
 */
object Pikassa {


    private var job: Job? = null

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
        onSuccess: (ResponseData) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        val body = BodyRequest(requestId, paymentMethod, details)
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    paymentRepository.requestPayment(uuid, apiKey, body)
                withContext(Dispatchers.Main) {
                    if (response.success) {
                        response.data?.let { onSuccess(it) }
                    } else {
                        response.error?.let { onError(it) }
                    }
                }
            } catch (ex: Exception) {
                // if we catch an error in working with request from sdk, call OnError and pass message in it
                withContext(Dispatchers.Main) {
                    var error = ex.getApiError()
                    if (error == null)
                        error = ResponseError(
                            PaymentErrorCode.SdkWorkError,
                            "inner sdk exception: ${ex.localizedMessage}"
                        )
                    onError(error)
                }
            }
        }
    }

    /**
     * method for closing coroutines to prevent io exceptions
     */
    fun close() {
        job?.cancel()
    }
}