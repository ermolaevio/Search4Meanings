package com.ermolaevio.search4meanings

import android.app.Application
import com.ermolaevio.search4meanings.di.AppComponent
import com.ermolaevio.search4meanings.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    lateinit var daggerAppComponent: AppComponent

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        daggerAppComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        daggerAppComponent.inject(this)
    }
}