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
    @SerializedName("5")
    ReceiveDataError("5"),
    @SerializedName("6")
    PaybackError("6"),
    @SerializedName("-1")
    SystemError("-1"),
    SdkWorkError("-2")
}