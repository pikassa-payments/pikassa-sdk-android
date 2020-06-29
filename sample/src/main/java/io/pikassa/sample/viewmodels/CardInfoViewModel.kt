package io.pikassa.sample.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
Created by Denis Chornyy on 29,Июнь,2020
All rights received.
 */
class CardInfoViewModel: ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var requestReceived = MutableLiveData<Boolean>()

    fun requestPayment() {

    }
}