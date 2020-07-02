package io.pikassa.sample.di.module

import dagger.Module
import dagger.Provides
import io.pikassa.sample.network.OrdersApi
import retrofit2.Retrofit

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */

@Module
class OrderServiceModule {
    @Provides
    fun providesOrderApi(retrofit: Retrofit): OrdersApi = retrofit.create(OrdersApi::class.java)
}