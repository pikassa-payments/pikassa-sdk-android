package io.pikassa.sample.entities

import android.os.Parcelable
import java.io.Serializable

/**
Created by pikassa, support@pikassa.io on 02,Июль,2020
All rights received.
 */
data class OrderData (
    val uuid: String,
    val customerPhone: String,
    val customerEmail: String,
    val amount: Float,
    val invoiceUuid: String,
    val successUrl: String,
    val failUrl: String
): Serializable