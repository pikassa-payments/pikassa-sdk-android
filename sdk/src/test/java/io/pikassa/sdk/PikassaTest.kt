package io.pikassa.sdk

import io.pikassa.sdk.entities.CardDetails
import io.pikassa.sdk.entities.PaymentMethod
import io.pikassa.sdk.entities.ResponseData
import io.pikassa.sdk.entities.ResponseError
import org.junit.Test

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */
class PikassaTest {
    @Test
    fun testWithoutRedirect() {
        Pikassa.init("be4d9881-4af5-4969-bac0-dfe8491a333a")
        val uuid = "e334d16e-d2d2-41a1-9076-5301703ee74e"
        val requestId = "40271fda28304550b2db2090ed5c3424"
        val paymentMethod = PaymentMethod.BANK_CARD
        val pan = "4111111111111111"
        val cardHolder = "ivan ivanov"
        val expYear = "19"
        val expMonth = "12"
        val cvc = "123"
        val someParam = mapOf(Pair("key1", "value1"), Pair("key2", 5))
        val details = CardDetails(pan, cardHolder, expYear, expMonth, cvc, someParam)

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