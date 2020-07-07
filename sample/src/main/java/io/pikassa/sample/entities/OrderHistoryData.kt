package io.pikassa.sample.entities

/**
Created by pikassa, support@pikassa.io on 02,Июль,2020
All rights received.
 */
data class OrderHistoryData(
    val uuid: String,
    val amount: Float,
    val status: StatusInvoice
)