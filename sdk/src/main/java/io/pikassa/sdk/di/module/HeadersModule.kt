package io.pikassa.sdk.di.module

import dagger.Module
import dagger.Provides

/**
Created by pikassa, support@pikassa.io on 26,Июнь,2020
All rights received.
 */

@Module
class HeadersModule {
    @Provides
    fun provideHeaders() = mutableListOf(Pair("Accept", "application/json; charset=utf-8"))
}