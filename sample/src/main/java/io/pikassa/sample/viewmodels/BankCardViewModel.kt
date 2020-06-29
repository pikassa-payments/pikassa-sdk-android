package io.pikassa.sample.viewmodels

import androidx.lifecycle.ViewModel
import io.pikassa.sample.utils.SingleLiveEvent
import io.pikassa.sdk.Pikassa
import io.pikassa.sdk.entities.CardDetails
import io.pikassa.sdk.entities.PaymentMethod
import io.pikassa.sdk.entities.ResponseData
import io.pikassa.sdk.entities.ResponseError
import java.util.*

/**
Created by Denis Chornyy on 29,Июнь,2020
All rights received.
 */
class BankCardViewModel : ViewModel() {
    companion object {
        // api key for payment request
        private const val apiKey: String = "be4d9881-4af5-4969-bac0-dfe8491a333a"
        private const val testUUID: String = "dd0425b1-8d47-4815-b68b-1fa7943c17e0"

    }

    var cardNum: String = "4111111111111111"
    var holder: String = "ivan ivanov"
    var cvc: String = "123"

    var isLoading = SingleLiveEvent<Boolean>()
    var errorReceived = SingleLiveEvent<ResponseError>()
    var requestReceived = SingleLiveEvent<ResponseData>()

    fun requestPayment() {
        isLoading.value = true
        val requestId = UUID.randomUUID().toString()
        Pikassa.init(apiKey)
        Pikassa.sendCardData(
            testUUID,
            requestId,
            PaymentMethod.BANK_CARD,
            CardDetails(cardNum, holder, cvc, null),
            onSuccess = {
                isLoading.value = false
                requestReceived.value = it
            },
            onError = {
                isLoading.value = false
                errorReceived.value = it
            }
        )
    }
}