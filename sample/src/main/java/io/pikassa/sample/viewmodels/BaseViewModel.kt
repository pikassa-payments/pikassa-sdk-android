package io.pikassa.sample.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.pikassa.sample.utils.InternetUtil
import io.pikassa.sample.utils.SingleLiveEvent

/**
Created by Denis Chornyy on 02,Июль,2020
All rights received.
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var noInternet = SingleLiveEvent<Boolean>()

    fun checkInternet() : Boolean {
        if(!InternetUtil.isInternetOn()) {
            noInternet.value = true
            return false
        }
        return true
    }
}