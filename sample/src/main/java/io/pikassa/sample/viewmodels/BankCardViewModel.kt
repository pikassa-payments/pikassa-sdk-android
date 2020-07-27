package io.pikassa.sample.viewmodels

import android.app.Application
import dev.icerock.moko.fields.FormField
import dev.icerock.moko.fields.liveBlock
import dev.icerock.moko.fields.validate
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import io.pikassa.sample.R
import io.pikassa.sample.entities.OrderData
import io.pikassa.sample.utils.SingleLiveEvent
import io.pikassa.sdk.Pikassa
import io.pikassa.sdk.entities.*
import java.util.*

/**
Created by pikassa, support@pikassa.io on 29,Июнь,2020
All rights received.
 */
class BankCardViewModel(application: Application, private val orderData: OrderData) :
    BaseViewModel(application) {
    companion object {
        // api key for payment request
        private const val API_KEY: String = "be4d9881-4af5-4969-bac0-dfe8491a333a"
    }

    var isLoading = SingleLiveEvent<Boolean>()
    var errorReceived = SingleLiveEvent<ResponseError>()
    var requestReceived = SingleLiveEvent<ResponseData>()
    val invoiceValue = "Оплатить ${orderData.amount} Р"

    val panField = FormField<String, StringDesc>(
        "",
        liveBlock { pan ->
            if (pan.isBlank() || pan.replace("\\s".toRegex(), "").length < application.resources.getInteger(R.integer.pan_amount))
                application.getString(R.string.error_pan).desc()
            else
                null
        })

    val holderField = FormField<String, StringDesc>(
        "",
        liveBlock { holder ->
            if (holder.isBlank() || !holder.matches(Regex("^[a-zA-Z_ ]*\$")))
                application.getString(R.string.error_holder).desc()
            else null
        })

    val expField = FormField<String, StringDesc>(
        "",
        liveBlock {
            if (it.length != 5) {
                application.getString(R.string.error_exp).desc()
            } else if (!checkYearValidation(getYearFromString(it)) || !checkMonthValidation(
                    getYearFromString(it),
                    getMonthFromString(it)
                )
            )
                application.getString(R.string.error_exp).desc()
            else null
        }
    )

    val cvcField = FormField<String, StringDesc>(
        "",
        liveBlock { cvc ->
            if(cvc.length != 3) {
                " ".desc()
            }
            else null
        })

    private val fields = listOf(panField, holderField, expField, cvcField)

    private fun getMonthFromString(value: String): String = value.substring(0, 2)
    private fun getYearFromString(value: String): String = value.substring(3)

    private fun checkYearValidation(expYear: String): Boolean {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR) - 2000
        return expYear.toIntOrNull() ?: 0 >= currentYear
    }

    private fun checkMonthValidation(expYear: String, expMonth: String): Boolean {
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString().substring(2).toInt()

        val placedYear = expYear.toIntOrNull() ?: 0
        val placedMonth = expMonth.toIntOrNull() ?: 0

        return when (currentYear) {
            placedYear -> {
                placedMonth >= currentMonth && placedMonth in 1..12 && expMonth.length == 2
            }
            else -> placedMonth in 1..12 && expMonth.length == 2
        }
    }

    fun requestPayment() {
        if (!fields.validate()) return
        if (!checkInternet()) return


        isLoading.value = true
        val requestId = UUID.randomUUID().toString()
        Pikassa.init(API_KEY)
        val details = mapOf(
            DetailsFields.PAN.field to panField.value().replace("\\s".toRegex(), ""),
            DetailsFields.CARD_HOLDER.field to holderField.value(),
            DetailsFields.EXP_YEAR.field to getYearFromString(expField.data.value),
            DetailsFields.EXP_MONTH.field to getMonthFromString(expField.data.value),
            DetailsFields.CVC.field to cvcField.value()
        )
        Pikassa.sendPaymentDetails(
            uuid = orderData.invoiceUuid,
            requestId = requestId,
            paymentMethod = PaymentMethod.BANK_CARD,
            details = details,
//            PaymentDetails(
//                panField.value().replace("\\s".toRegex(), ""),
//                holderField.value(),
//                getYearFromString(expField.data.value),
//                getMonthFromString(expField.data.value),
//                cvcField.value(),
//                null
//            ),
            onSuccess = {
                isLoading.value = false
                requestReceived.value = it
            },
            onError = {
                isLoading.value = false
                errorReceived.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        Pikassa.close()
    }
}