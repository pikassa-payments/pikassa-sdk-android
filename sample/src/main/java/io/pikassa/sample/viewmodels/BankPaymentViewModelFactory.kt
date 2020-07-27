package io.pikassa.sample.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.pikassa.sample.entities.OrderData

/**
Created by pikassa, support@pikassa.io on 02,Июль,2020
All rights received.
 */
class BankPaymentViewModelFactory(
    private val application: Application,
    private val myOrderData: OrderData
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        BankPaymentViewModel(application, myOrderData) as T
}