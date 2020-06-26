package io.pikassa.sdk.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */

@Module
class HttpClientModule {
    @Provides
    fun provideHttpClientModule(headers: List<Pair<String, String>>): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor {
            val request = it.request().newBuilder()
            for (pair in headers) {
                request.header(pair.first, pair.second)
            }
            it.proceed(request.build())
        }
        return httpClient
    }
}