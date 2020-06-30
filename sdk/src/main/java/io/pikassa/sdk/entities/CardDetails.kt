package io.pikassa.sdk.entities

/**
Created by Denis Chornyy on 25,Июнь,2020
All rights received.
 */

/**
 * class representing detail information about bank card
 * @param pan card identifier
 * @param cardHolder name surname of a card holder
 * @param cvc number from the bottom side of bank card
 * @param someParam additional params
 */
data class CardDetails(
    val pan: String,
    val cardHolder: String,
    val expYear: String,
    val expMonth: String,
    val cvc: String,
    val someParam: Map<String, Any>?
)