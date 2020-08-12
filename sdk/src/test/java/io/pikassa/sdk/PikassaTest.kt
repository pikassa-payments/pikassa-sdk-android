package io.pikassa.sdk

import io.pikassa.sdk.entities.CardDetails
import io.pikassa.sdk.entities.PaymentMethod
import io.pikassa.sdk.entities.ResponseData
import io.pikassa.sdk.entities.ResponseError
import org.junit.Test
import org.junit.internal.runners.JUnit38ClassRunner
import org.junit.runner.RunWith
import org.junit.runner.Runner
import org.junit.runners.JUnit4
import org.junit.runners.Suite

/**
Created by pikassa, support@pikassa.io on 26,Июнь,2020
All rights received.
 */
class PikassaTest {
    @Test
    fun testWithoutRedirect() {
        Pikassa.init("be4d9881-4af5-4969-bac0-dfe8491a333a")
        val uuid = "f7b22418-6c1e-45bb-b8a7-d1ef7b5f5c601"
        val requestId = "40271fda28304550b2db2090ed5c3424"
        val pan = "4111111111111111"
        val cardHolder = "ivan ivanov"
        val expYear = "19"
        val expMonth = "12"
        val cvc = "123"
        val someParam = mapOf(Pair("key1", "value1"), Pair("key2", 5))
        val paymentMethod =
            PaymentMethod.BankCard(CardDetails(pan, cardHolder, expYear, expMonth, cvc, someParam))

        try {
            Pikassa.sendPaymentData(
                uuid,
                requestId,
                paymentMethod,
                onSuccess = { showSuccessOutput(it) },
                onError = { showErrorOutput(it) }
            )
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