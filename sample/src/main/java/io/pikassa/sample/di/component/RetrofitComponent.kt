package io.pikassa.sample.di.component

import dagger.Component
import io.pikassa.sample.di.module.GsonConverterModule
import io.pikassa.sample.di.module.HeadersModule
import io.pikassa.sample.di.module.HttpClientModule
import io.pikassa.sample.di.module.OrderServiceModule
import io.pikassa.sample.di.module.RetrofitModule
import io.pikassa.sample.di.module.UrlModule
import io.pikassa.sample.helpers.OrderNetworkHelper
import io.pikassa.sdk.helpers.PaymentHelper
import javax.inject.Singleton

/**
Created by Denis Chornyy on 26,Июнь,2020
All rights received.
 */

@Component(modules = [GsonConverterModule::class, HeadersModule::class, HttpClientModule::class, OrderServiceModule::class, UrlModule::class, RetrofitModule::class])
@Singleton
interface RetrofitComponent {
    fun inject(ordersHelper: OrderNetworkHelper)
}