package io.pikassa.sample

import android.app.Application
import io.pikassa.sample.utils.InternetUtil

/**
Created by pikassa, support@pikassa.io on 02,Июль,2020
All rights received.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        InternetUtil.init(this)
    }
}