package com.ermolaevio.search4meanings

import android.app.Application
import com.ermolaevio.search4meanings.di.AppComponent
import com.ermolaevio.search4meanings.di.DaggerAppComponent

class App: Application() {

    lateinit var daggerAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        daggerAppComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}