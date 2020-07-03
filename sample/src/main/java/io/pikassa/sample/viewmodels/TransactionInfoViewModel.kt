package io.pikassa.sample.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.pikassa.sample.entities.OrderError
import io.pikassa.sample.entities.OrderHistoryData
import io.pikassa.sample.helpers.OrderNetworkHelper
import io.pikassa.sample.repository.OrdersRepository
import io.pikassa.sample.utils.SingleLiveEvent
import kotlinx.coroutines.*

/**
Created by Denis Chornyy on 29,Июнь,2020
All rights received.
 */
class TransactionInfoViewModel(application: Application, private val uuid: String) : BaseViewModel(application) {

    companion object {
        const val DELAY = 2000L
    }

    var newUUID = MutableLiveData<String>()
    var newAmount = MutableLiveData<String>()
    var newStatus = MutableLiveData<String>()
    var newTime = MutableLiveData<String>()
    var error = SingleLiveEvent<OrderError>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val orderRepository = OrdersRepository(OrderNetworkHelper())

    fun getStatus() {

        coroutineScope.launch {
            //cycling the statuses from server
            while(true) {
                val response = orderRepository.getStatus(uuid)
                if(response.success) {
                    newUUID.postValue(response.data?.uuid)
                    newAmount.postValue(response.data?.amount.toString())
                    newStatus.postValue(response.data?.status?.code?.getDescription())
                    newTime.postValue(response.data?.status?.time)
                }
                else {
                    error.postValue(response.error)
                }
                delay(DELAY)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}