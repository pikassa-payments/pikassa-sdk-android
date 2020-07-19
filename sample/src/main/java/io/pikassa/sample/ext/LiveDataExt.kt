package io.pikassa.sample.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
Created by pikassa, support@pikassa.io on 03,Июль,2020
All rights received.
 */

fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
    removeObserver(observer)
    observe(owner, observer)
}