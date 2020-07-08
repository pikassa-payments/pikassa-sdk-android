package io.pikassa.sample.viewmodels

import android.app.Application
import dev.icerock.moko.fields.FormField
import dev.icerock.moko.fields.liveBlock
import dev.icerock.moko.fields.validate
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import io.pikassa.sample.R
import io.pikassa.sample.utils.SingleLiveEvent
import io.pikassa.sdk.Pikassa
import io.pikassa.sdk.entities.CardDetails
import io.pikassa.sdk.entities.PaymentMethod
import io.pikassa.sdk.entities.ResponseData
import io.pikassa.sdk.entities.ResponseError
import java.util.*

/**
Created by pikassa, support@pikassa.io on 29,Июнь,2020
All rights received.
 */
class BankCardViewModel(application: Application, private val uuid: String) :
    BaseViewModel(application) {
    companion object {
        // api key for payment request
        private const val API_KEY: String = "be4d9881-4af5-4969-bac0-dfe8491a333a"
    }

    var isLoading = SingleLiveEvent<Boolean>()
    var errorReceived = SingleLiveEvent<ResponseError>()
    var requestReceived = SingleLiveEvent<ResponseData>()

    val panField = FormField<String, StringDesc>(
        application.resources.getString(R.string.test_pan),
        liveBlock { pan ->
            if (pan.isBlank() || pan.length < application.resources.getInteger(R.integer.pan_amount))
                application.getString(R.string.error_invalid_amount_of_numbers).desc()
            else
                null
        })

    val holderField = FormField<String, StringDesc>(
        application.resources.getString(R.string.test_holder),
        liveBlock { holder ->
            if (holder.isBlank())
                application.getString(R.string.field_empty_error).desc()
            else null
        })

    val expYearLD =
        androidx.lifecycle.MutableLiveData(application.resources.getString(R.string.test_exp_year))
    val expMonthLD =
        androidx.lifecycle.MutableLiveData(application.resources.getString(R.string.test_exp_month))
    val expYearError = androidx.lifecycle.MutableLiveData("")
    val expMonthError = androidx.lifecycle.MutableLiveData("")


    val cvcField = FormField<String, StringDesc>(
        application.resources.getString(R.string.test_cvc),
        liveBlock { cvc ->
            if (cvc.isBlank() || cvc.length != application.resources.getInteger(R.integer.cvc_amount))
                application.getString(R.string.error_invalid_amount_of_numbers).desc()
            else
                null
        })

    private val fields = listOf(panField, holderField, cvcField)

    private fun checkYearValidation(): Boolean {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR) - 2000
        return expYearLD.value!!.toInt() >= currentYear
    }

    private fun checkMonthValidation(): Boolean {
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString().substring(2).toInt()

        val placedYear = expYearLD.value?.toInt() ?: 0
        val placedMonth = expMonthLD.value?.toInt() ?: 0
        val monthStr = expMonthLD.value ?: ""

        return when (currentYear) {
            placedYear -> {
                placedMonth >= currentMonth && placedMonth in 1..12 && monthStr.length == 2
            }
            else -> placedMonth in 1..12 && monthStr.length == 2
        }
    }

    fun requestPayment() {
        var success = true
        if (!checkYearValidation()) {
            expYearError.value = "Проверьте парвильность введенных данных!"
            success = false
        } else expYearError.value = null
        if (!checkMonthValidation()) {
            expMonthError.value = "Проверьте правильность введенных данных"
            success = false
        } else expMonthError.value = null
        if (!fields.validate() || !success) return
        if (!checkInternet()) return


        isLoading.value = true
        val requestId = UUID.randomUUID().toString()
        Pikassa.init(API_KEY)
        Pikassa.sendCardData(
            uuid,
            requestId,
            PaymentMethod.BANK_CARD,
            CardDetails(
                panField.value(),
                holderField.value(),
                expYearLD.value.toString(),
                expMonthLD.value.toString(),
                cvcField.value(),
                null
            ),
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