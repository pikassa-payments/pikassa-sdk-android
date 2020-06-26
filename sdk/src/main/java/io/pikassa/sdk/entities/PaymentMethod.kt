package io.pikassa.sdk.entities

/**
Created by Denis Chornyy on 25,Июнь,2020
All rights received.
 */
/**
 * Class representing types of in-app payment
 */
enum class PaymentMethod(val method: String) {
    BANK_CARD("BankCard"),
    WEBMONEY("WMR"),
    YANDEX_MONEY("YandexMoney"),
    MOBILE("Mobile")
}