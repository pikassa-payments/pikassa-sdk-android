package io.pikassa.sample.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        httpClient: OkHttpClient.Builder,
        url: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(httpClient.build())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}