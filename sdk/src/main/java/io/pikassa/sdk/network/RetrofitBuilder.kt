package io.pikassa.sdk.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */
object RetrofitBuilder {
    private const val BASE_URL = "https://pikassa.io/merchant-api/api/v2/"
    //Списки header
    private var headers: MutableList<Pair<String, String>> = mutableListOf()

    //todo: add dependency injection

    // для добавления header
    private var httpClient: OkHttpClient.Builder

    init {
        // add headers in constructor
        headers.add(Pair("Accept", "application/json; charset=utf-8"))

        httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            for (pair in headers) {
                request.header(pair.first, pair.second)
            }
            chain.proceed(request.build())
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val paymentApi: PaymentApi = getRetrofit().create(PaymentApi::class.java)
}