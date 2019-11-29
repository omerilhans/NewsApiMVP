package com.omerilhanli.newsapimvp.di.component

import com.omerilhanli.newsapimvp.di.module.ApiModule
import com.omerilhanli.newsapimvp.di.module.ContractModule
import com.omerilhanli.newsapimvp.mvvm.view.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, ContractModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}