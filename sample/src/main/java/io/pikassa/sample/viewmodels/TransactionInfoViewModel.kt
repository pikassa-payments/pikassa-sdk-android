package io.pikassa.sample.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.pikassa.sample.entities.InvoiceStatus
import io.pikassa.sample.entities.OrderError
import io.pikassa.sample.entities.OrderHistoryData
import io.pikassa.sample.entities.StatusInvoice
import io.pikassa.sample.helpers.OrderNetworkHelper
import io.pikassa.sample.repository.OrdersRepository
import io.pikassa.sample.utils.SingleLiveEvent
import kotlinx.coroutines.*

/**
Created by pikassa, support@pikassa.io on 29,Июнь,2020
All rights received.
 */
class TransactionInfoViewModel(application: Application, private val uuid: String) :
    BaseViewModel(application) {

    companion object {
        const val DELAY = 2000L
    }
    var error = SingleLiveEvent<OrderError>()
    var checkSum = SingleLiveEvent<String>()
    var orderID = SingleLiveEvent<String>()
    var orderStatus = SingleLiveEvent<InvoiceStatus>()
    var newOrder = SingleLiveEvent<Boolean>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val orderRepository = OrdersRepository(OrderNetworkHelper())

    fun getStatus() {

        coroutineScope.launch {
            //cycling the statuses from server
            while (true) {
                val response = orderRepository.getStatus(uuid)
                if (response.success) {
                    checkSum.postValue(response.data?.amount.toString() + " Р")
                    orderID.postValue(response.data?.uuid)
                    orderStatus.postValue(response.data?.status?.code)
                } else {
                    error.postValue(response.error)
                }
                delay(DELAY)
            }
        }
    }

    fun newOrder() = newOrder.postValue(true)

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}