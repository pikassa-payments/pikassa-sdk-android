package io.pikassa.sdk.entities

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */
/**
 * Class representing body of payment request
 */
data class BodyRequest (
    val requestId: String,
    val paymentMethod: PaymentMethod,
    val details: CardDetails
)