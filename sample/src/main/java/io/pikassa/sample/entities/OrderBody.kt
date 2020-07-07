package io.pikassa.sample.entities

/**
Created by pikassa, support@pikassa.io on 02,Июль,2020
All rights received.
 */

/**
 * data class representing order's body
 * @param items items to buy by customer
 * @param customerPhone info phone
 * @param customerEmail info email
 */
data class OrderBody (
    val items: List<Item>,
    val customerPhone: String,
    val customerEmail: String
)