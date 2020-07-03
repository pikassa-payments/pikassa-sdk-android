package io.pikassa.sdk.entities

import com.google.gson.annotations.SerializedName
import io.pikassa.sdk.entities.interfaces.IDescription

/**
Created by Denis Chornyy on 25,Июнь,2020
All rights received.
 */
enum class PaymentErrorCode(val code: String) : IDescription {
    @SerializedName("1")
    CreateAccountError("1") {
        override fun getDescription(): String = "1 - Ошибка создания счета"
    },

    @SerializedName("2")
    AuthorizeAccountError("2") {
        override fun getDescription(): String = "2 - Ошибка авторизации платежа"
    },

    @SerializedName("3")
    CancelPaymentError("3") {
        override fun getDescription(): String = "3 - Ошибка отмены платежа"
    },

    @SerializedName("5")
    ReceiveDataError("5") {
        override fun getDescription(): String = "5 - Ошибка при получении данных"
    },

    @SerializedName("6")
    PaybackError("6") {
        override fun getDescription(): String = "6 - Ошибка выполнения возврата"
    },

    @SerializedName("-1")
    SystemError("-1") {
        override fun getDescription(): String = "-1 - Системная ошибка"
    },
    SdkWorkError("-2") {
        override fun getDescription(): String = "Внутренняя ошибка sdk pikassa"
    }
}