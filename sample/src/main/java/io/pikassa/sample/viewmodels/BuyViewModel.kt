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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */
class BuyViewModel(application: Application) : AndroidViewModel(application) {
    // loading data
    var isLoading = SingleLiveEvent<Boolean>()
    var paymentCreated = SingleLiveEvent<Boolean>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val amountField = FormField<String, StringDesc>("", liveBlock { kilos ->
        if (kilos.isBlank() || kilos.toIntOrNull() == null)
            application.getString(R.string.amount_error).desc()
        else
            null
    })

    private val fields = listOf(amountField)


    fun postBuy() {
        if(!fields.validate()) return
        isLoading.value = true
        coroutineScope.launch {
            delay(500L)
            isLoading.postValue(false)
            paymentCreated.postValue(true)
        }
    }
}