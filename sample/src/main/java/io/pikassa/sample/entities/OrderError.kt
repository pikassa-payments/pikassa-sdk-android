package io.pikassa.sample.entities

/**
Created by Denis Chornyy on 02,Июль,2020
All rights received.
 */
data class OrderError (
    val Code: String,
    val Message: String,
    val CorrelationId: String?
) {
    override fun toString(): String {
        return "Something went wrong!\nerror code: $Code\nerror message: $Message"
    }
}