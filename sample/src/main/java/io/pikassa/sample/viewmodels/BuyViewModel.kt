package io.pikassa.sample.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */
class BuyViewModel : ViewModel() {
    // loading data
    var isLoading = MutableLiveData<Boolean>()
    var paymentCreated = MutableLiveData<Boolean>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun validateAmount() {

    }

    fun postBuy() {
        isLoading.value = true
        coroutineScope.launch {
            delay(3000L)
            isLoading.postValue(false)
            paymentCreated.postValue(true)
        }
    }
}