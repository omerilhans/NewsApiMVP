package com.omerilhanli.newsapimvp

import android.app.Application
import com.omerilhanli.newsapimvp.di.component.AppComponent
import com.omerilhanli.newsapimvp.di.component.DaggerAppComponent

class App : Application() {

    var component: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.create()
    }
}