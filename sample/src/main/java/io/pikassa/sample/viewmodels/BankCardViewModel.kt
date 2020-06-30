package io.pikassa.sample.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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
Created by Denis Chornyy on 29,Июнь,2020
All rights received.
 */
class BankCardViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        // api key for payment request
        private const val API_KEY: String = "be4d9881-4af5-4969-bac0-dfe8491a333a" + "error"
        private const val TEST_UUID: String = "dd0425b1-8d47-4815-b68b-1fa7943c17e0"
    }

    var isLoading = SingleLiveEvent<Boolean>()
    var errorReceived = SingleLiveEvent<ResponseError>()
    var requestReceived = SingleLiveEvent<ResponseData>()

    val panField = FormField<String, StringDesc>(
        application.resources.getString(R.string.test_pan),
        liveBlock { pan ->
            if (pan.isBlank() || pan.length != application.resources.getInteger(R.integer.pan_amount))
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

    val expYearField = FormField<String, StringDesc>(
        application.resources.getString(R.string.test_exp_year),
        liveBlock { expYear ->
            if (expYear.isBlank() || expYear.length != application.resources.getInteger(R.integer.exp_year_amount))
                application.resources.getString(R.string.error_invalid_amount_of_numbers).desc()
            else null
        })

    val expMonthField = FormField<String, StringDesc>(
        application.resources.getString(R.string.test_exp_month),
        liveBlock { expYear ->
            if (expYear.isBlank() || expYear.length != application.resources.getInteger(R.integer.exp_month_amount))
                application.resources.getString(R.string.error_invalid_amount_of_numbers).desc()
            else null
        })

    val cvcField = FormField<String, StringDesc>(
        application.resources.getString(R.string.test_cvc),
        liveBlock { cvc ->
            if (cvc.isBlank() || cvc.length != application.resources.getInteger(R.integer.cvc_amount))
                application.getString(R.string.error_invalid_amount_of_numbers).desc()
            else
                null
        })

    private val fields = listOf(panField, holderField, cvcField, expYearField, expMonthField)

    fun requestPayment() {
        if (!fields.validate()) return

        isLoading.value = true
        val requestId = UUID.randomUUID().toString()
        Pikassa.init(API_KEY)
        Pikassa.sendCardData(
            TEST_UUID,
            requestId,
            PaymentMethod.BANK_CARD,
            CardDetails(
                panField.value(),
                holderField.value(),
                expYearField.value(),
                expMonthField.value(),
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
}