package io.pikassa.sdk.di.module

import dagger.Module
import dagger.Provides
import io.pikassa.sdk.network.PaymentApi
import retrofit2.Retrofit

/**
Created by pikassa, support@pikassa.io on 26,Июнь,2020
All rights received.
 */

@Module
class PaymentServiceModule {
    @Provides
    fun providesPaymentApi(retrofit: Retrofit): PaymentApi = retrofit.create(PaymentApi::class.java)
}