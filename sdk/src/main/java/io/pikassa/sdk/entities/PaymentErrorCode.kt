package io.pikassa.sdk.entities

/**
Created by Denis Chornyy on 25,Июнь,2020
All rights received.
 */
enum class PaymentErrorCode(val code: String) {
    CreateAccountError("1"),
    AuthorizeAccountError("2"),
    CancelPaymentError("3"),
    ReceiveDataError("4"),
    PaybackError("5"),
    SystemError("6")
}