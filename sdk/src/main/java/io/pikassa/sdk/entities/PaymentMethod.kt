package io.pikassa.sdk.entities

import io.pikassa.sdk.entities.PaymentMethod.Custom.Companion.DATA_KEY_PAYMENT_METHOD

/**
Created by pikassa, support@pikassa.io on 25,Июнь,2020
All rights received.
 */
/**
 * Class representing types of in-app payment
 */


// TODO  Support types for other methods:
//    WEBMONEY - "WMR",
//    "YandexMoney",
//    "Mobile"

sealed class PaymentMethod<D> {
    abstract val details: D

    data class BankCard(override val details: CardDetails) : PaymentMethod<CardDetails>()

    data class Custom(override val details: Map<String, String>) :
        PaymentMethod<Map<String, String>>() {
        companion object {
            const val DATA_KEY_PAYMENT_METHOD = "paymentMethod"
        }
    }


    fun getApiAlias(): String {
        return when (this) {
            is BankCard -> ALIAS_BANK_CARD
            is Custom -> details[DATA_KEY_PAYMENT_METHOD] ?: ALIAS_UNKNOWN
        }
    }


    companion object {
        private const val ALIAS_BANK_CARD = "BankCard"
        private const val ALIAS_UNKNOWN = "Unknown"

    }
}