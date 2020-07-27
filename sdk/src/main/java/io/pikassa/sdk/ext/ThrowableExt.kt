package io.pikassa.sdk.ext

import com.google.gson.Gson
import io.pikassa.sdk.entities.PaymentDetailResponse
import io.pikassa.sdk.entities.ResponseError
import retrofit2.HttpException
import java.lang.Exception

/**
Created by pikassa, support@pikassa.io on 02,Июль,2020
All rights received.
 */

fun Exception.getApiError(): ResponseError? {
    if (this is HttpException) {
        try {
            val errorJson = this.response()?.errorBody()?.string()
            val obj = Gson().fromJson(errorJson, PaymentDetailResponse::class.java)
            return obj.error
        } catch (ex: Exception) {
            println(ex.localizedMessage)
        }
    }
    return null
}