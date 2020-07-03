package io.pikassa.sample.viewmodels

import android.app.Application
import dev.icerock.moko.fields.FormField
import dev.icerock.moko.fields.liveBlock
import dev.icerock.moko.fields.validate
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import io.pikassa.sample.R
import io.pikassa.sample.entities.Item
import io.pikassa.sample.entities.OrderData
import io.pikassa.sample.entities.OrderError
import io.pikassa.sample.helpers.OrderNetworkHelper
import io.pikassa.sample.repository.OrdersRepository
import io.pikassa.sample.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */
class BuyViewModel(application: Application) : BaseViewModel(application) {
    // loading data
    var isLoading = SingleLiveEvent<Boolean>()
    var isError = SingleLiveEvent<OrderError>()
    var paymentCreated = SingleLiveEvent<OrderData>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val amountField = FormField<String, StringDesc>("123", liveBlock { kilos ->
        if (kilos.isBlank() || kilos.toIntOrNull() == null)
            application.getString(R.string.amount_error).desc()
        else
            null
    })

    private val fields = listOf(amountField)


    fun postBuy() {
        if(!fields.validate()) return
        if(!checkInternet()) return
        isLoading.value = true
        coroutineScope.launch {
            val ordersRepository = OrdersRepository(OrderNetworkHelper())

            val testItems = listOf(Item(1, 2), Item(2, 6))
            val testEmail = "test@test.ru"
            val testPhone = "88005553535"

            val response = ordersRepository.makeOrder(testItems, testPhone, testEmail)
            isLoading.postValue(false)
            if(response.success) {
                paymentCreated.postValue(response.data)
            }
            else {
                isError.postValue(response.error)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}