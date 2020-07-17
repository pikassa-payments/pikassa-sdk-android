package io.pikassa.sample.utils

import com.google.gson.Gson
import io.pikassa.sample.entities.OrderError
import io.pikassa.sample.entities.OrderResponse
import retrofit2.HttpException

/**
Created by pikassa, support@pikassa.io on 02,Июль,2020
All rights received.
 */

fun Exception.getError(): OrderError? {
    if (this is HttpException) {
        try {
            val errorJson = this.response()?.errorBody()?.string()
            val obj = Gson().fromJson(errorJson, OrderResponse::class.java)
            return obj.error
        } catch (ex: Exception) {
            println(ex.localizedMessage)
        }
    }
    return null
}