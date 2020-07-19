package io.pikassa.sdk.entities

/**
Created by pikassa, support@pikassa.io on 25,Июнь,2020
All rights received.
 */

/**
 * Class for errors
 * @param code error code
 * @param message error message
 */
data class ResponseError(
    val code: PaymentErrorCode,
    val message: String
) {
    override fun toString(): String {
        return "error code: ${code.getDescription()}\nerror message: $message"
    }
}