package io.pikassa.sdk.di.component

import dagger.Component
import io.pikassa.sdk.di.module.*
import io.pikassa.sdk.helpers.PaymentHelper
import javax.inject.Singleton

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */

@Component(modules = [GsonConverterModule::class, HeadersModule::class, HttpClientModule::class, PaymentServiceModule::class, UrlModule::class, RetrofitModule::class])
@Singleton
interface RetrofitComponent {
    fun inject(helper: PaymentHelper)
}