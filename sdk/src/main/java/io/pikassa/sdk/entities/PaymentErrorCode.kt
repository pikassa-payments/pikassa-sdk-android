package io.pikassa.sdk.entities

import com.google.gson.annotations.SerializedName

/**
Created by Denis Chornyy on 25,Июнь,2020
All rights received.
 */
enum class PaymentErrorCode(val code: String) {
    @SerializedName("1")
    CreateAccountError("1"),
    @SerializedName("2")
    AuthorizeAccountError("2"),
    @SerializedName("3")
    CancelPaymentError("3"),
    @SerializedName("4")
    ReceiveDataError("4"),
    @SerializedName("5")
    PaybackError("5"),
    @SerializedName("6")
    SystemError("6")
}