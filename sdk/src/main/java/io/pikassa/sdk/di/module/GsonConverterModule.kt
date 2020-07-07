package io.pikassa.sdk.di.module

import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory

/**
Created by pikassa, support@pikassa.io on 26,Июнь,2020
All rights received.
 */

@Module
class GsonConverterModule {
    @Provides
    fun provideGsonConverter() = GsonConverterFactory.create()
}