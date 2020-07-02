package io.pikassa.sample.entities

/**
Created by Denis Chornyy on 02,Июль,2020
All rights received.
 */

/**
 * data class representing order item
 * @param code item identificator for shop
 * @param count amount of things to buy
 */
data class Item(
    val code: Int,
    val count: Int
)