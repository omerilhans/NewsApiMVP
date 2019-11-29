package com.omerilhanli.newsapimvp.mvvm.contract

interface BaseContract {

    interface BaseView {
        fun handleError(msg: String)
    }

    interface BasePresenter<V> {
        var view: V?
    }
}