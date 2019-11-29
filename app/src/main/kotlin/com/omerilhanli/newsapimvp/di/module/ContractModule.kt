package com.omerilhanli.newsapimvp.di.module

import com.omerilhanli.newsapimvp.mvvm.contract.MainContract
import com.omerilhanli.newsapimvp.mvvm.contract.NewsContract
import com.omerilhanli.newsapimvp.mvvm.model.MainModel
import com.omerilhanli.newsapimvp.mvvm.model.NewsModel
import com.omerilhanli.newsapimvp.mvvm.presenter.MainPresenter
import com.omerilhanli.newsapimvp.mvvm.presenter.NewsPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class ContractModule {

    @Binds
    abstract fun provideMainPresenter(presenter: MainPresenter): MainContract.Presenter

    @Binds
    abstract fun provideNewsPresenter(presenter: NewsPresenter): NewsContract.Presenter

    @Binds
    abstract fun provideNewsModel(model: NewsModel): NewsContract.Model

    @Binds
    abstract fun provideMainModel(model: MainModel): MainContract.Model
}