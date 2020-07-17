package io.pikassa.sample.entities

/**
Created by pikassa, support@pikassa.io on 02,Июль,2020
All rights received.
 */
data class OrderResponse(
    val success: Boolean,
    val data: OrderData?,
    val error: OrderError?
) {
    override fun toString(): String {
        return "success: $success\n${if(success) data.toString() else error.toString()}"
    }
}