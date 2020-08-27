package io.pikassa.sdk.entities

/**
Created by pikassa, support@pikassa.io on 25,Июнь,2020
All rights received.
 */

/**
 * class representing detail information about bank card
 * @param pan card identifier
 * @param cardHolder name surname of a card holder
 * @param expYear card expiration (year)
 * @param expMonth card expiration (month)
 * @param cvc number from the bottom side of bank card
 */
data class CardDetails(
    val pan: String,
    val cardHolder: String,
    val expYear: String,
    val expMonth: String,
    val cvc: String
)