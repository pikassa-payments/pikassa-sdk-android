package io.pikassa.sdk

import io.pikassa.sdk.entities.CardDetails
import io.pikassa.sdk.entities.PaymentMethod
import org.junit.Test

class PaymentMethodTest {


    @Test
    fun getApiAliasWhenBankCardThenTextBankCard() {
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

        assert(paymentMethod.getApiAlias() == "BankCard")
    }

    @Test
    fun getAdiAliasWhenCustomWithSetPaymentMethodThenSeatedMPaymentMethod() {
        val expectedMethod = "SomeMethod"
        val paymentMethod =
            PaymentMethod.Custom(mapOf(PaymentMethod.Custom.DATA_KEY_PAYMENT_METHOD to expectedMethod))

        assert(paymentMethod.getApiAlias() == expectedMethod)
    }

    @Test
    fun getAdiAliasWhenCustomWithoutSetPaymentMethodThenUnknown() {
        val expectedMethod = "Unknown"
        val paymentMethod =
            PaymentMethod.Custom(mapOf())

        assert(paymentMethod.getApiAlias() == expectedMethod)
    }
}