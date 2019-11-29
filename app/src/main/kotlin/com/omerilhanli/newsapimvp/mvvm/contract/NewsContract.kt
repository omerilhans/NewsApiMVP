package com.omerilhanli.newsapimvp.mvvm.contract

import com.omerilhanli.newsapimvp.mvvm.model.entity.ViewVisibility
import com.omerilhanli.newsapimvp.mvvm.model.entity.News
import io.reactivex.Observable
import java.util.*

interface NewsContract {

    interface Model {
        fun getNews(): Observable<News>?
        fun getDateFrom10DayAgo(): Date?

        var textQuery: String
        var selectedDate: String
    }

    interface View : BaseContract.BaseView {
        fun displayNews(news: News?)
        fun openUrlOnWebTabBrowser(url:String)
    }

    interface Presenter : BaseContract.BasePresenter<View> {
        val visibilityProgress: ViewVisibility
        val visibilityList: ViewVisibility
        var isNetworkStatus: (() -> Boolean)?

        fun setTextQuery(query: String)
        fun setDate(date: Date?)

        fun fetchNews()
        fun openUrlOnWebTabBrowser(url:String)
    }
}