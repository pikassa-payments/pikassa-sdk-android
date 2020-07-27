package io.pikassa.sdk.entities

/**
Created by pikassa, support@pikassa.io on 25,Июнь,2020
All rights received.
 */

/**
 * Class representing response from bank card request
 * @param success the bank request result
 * @param data
 */
data class PaymentDetailResponse(
    val success: Boolean,
    val data: ResponseData?,
    val error: ResponseError?
)