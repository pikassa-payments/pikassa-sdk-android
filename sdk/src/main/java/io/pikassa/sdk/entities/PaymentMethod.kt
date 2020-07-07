package io.pikassa.sdk.entities

import com.google.gson.annotations.SerializedName

/**
Created by pikassa, support@pikassa.io on 25,Июнь,2020
All rights received.
 */
/**
 * Class representing types of in-app payment
 */
enum class PaymentMethod(val method: String) {
    @SerializedName("BankCard")
    BANK_CARD("BankCard"),
    @SerializedName("WMR")
    WEBMONEY("WMR"),
    @SerializedName("YandexMoney")
    YANDEX_MONEY("YandexMoney"),
    @SerializedName("Mobile")
    MOBILE("Mobile")
}