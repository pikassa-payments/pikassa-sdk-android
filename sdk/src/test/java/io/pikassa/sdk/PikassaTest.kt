package io.pikassa.sdk

import io.pikassa.sdk.entities.*
import org.junit.Test

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
        val paymentMethod = PaymentMethod.BANK_CARD
        val pan = "4111111111111111"
        val cardHolder = "ivan ivanov"
        val expYear = "19"
        val expMonth = "12"
        val cvc = "123"
        val details = mapOf(
            DetailsFields.PAN.field to pan,
            DetailsFields.CARD_HOLDER.field to cardHolder,
            DetailsFields.EXP_YEAR.field to expYear,
            DetailsFields.EXP_MONTH.field to expMonth,
            DetailsFields.CVC.field to cvc
        )

        try {
            Pikassa.sendPaymentDetails(
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