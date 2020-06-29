package io.pikassa.sdk

import io.pikassa.sdk.entities.CardDetails
import io.pikassa.sdk.entities.PaymentMethod
import io.pikassa.sdk.entities.ResponseData
import io.pikassa.sdk.entities.ResponseError
import org.junit.Test
import java.util.*

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */
class PikassaTest {
    @Test
    fun baseTest() {
        Pikassa.init("be4d9881-4af5-4969-bac0-dfe8491a333a")
        val uuid = "dd0425b1-8d47-4815-b68b-1fa7943c17e0"
        val requestId = UUID.randomUUID().toString()
        val paymentMethod = PaymentMethod.BANK_CARD
        val pan = "4111111111111111"
        val cardHolder = "ivan ivanov"
        val cvc = "123"
        val someParam = mapOf(Pair("key1", "value1"), Pair("key2", 5))
        val details = CardDetails(pan, cardHolder, cvc, someParam)

        try {
            Pikassa.sendCardData(
                uuid,
                requestId,
                paymentMethod,
                details, onSuccess = {
                    showSuccessOutput(it)
                }, onError = {
                    showErrorOutput(it)
                })
        } catch (ex: Exception) {
            println("${ex.localizedMessage} in ${ex.stackTrace}")
        }
    }

    private fun showSuccessOutput(output: ResponseData) {
        println(output.toString())
    }

    private fun showErrorOutput(output: ResponseError) {
        println(output.toString())
    }
}