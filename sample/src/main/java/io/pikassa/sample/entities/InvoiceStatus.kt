package io.pikassa.sample.entities

import com.google.gson.annotations.SerializedName
import io.pikassa.sdk.entities.interfaces.IDescription

/**
Created by Denis Chornyy on 03,Июль,2020
All rights received.
 */
enum class InvoiceStatus(val status: Int):IDescription {
    @SerializedName("1")
    New(1) {
        override fun getDescription(): String = "New"
    },
    @SerializedName("2")
    InvoiceCreated(2) {
        override fun getDescription(): String = "InvoiceCreated"
    },
    @SerializedName("3")
    Payed(3) {
        override fun getDescription(): String = "Payed"
    },
    @SerializedName("4")
    Failed(4) {
        override fun getDescription(): String = "Failed"
    },
    @SerializedName("5")
    Refunded(5) {
        override fun getDescription(): String = "Refunded"
    },
    @SerializedName("6")
    PartlyRefunded(6) {
        override fun getDescription(): String = "PartlyRefunded"
    },
    @SerializedName("7")
    Canceled(7) {
        override fun getDescription(): String = "Canceled"
    },
    @SerializedName("9")
    PreAuthorized(9) {
        override fun getDescription(): String = "PreAuthorized"
    }
}