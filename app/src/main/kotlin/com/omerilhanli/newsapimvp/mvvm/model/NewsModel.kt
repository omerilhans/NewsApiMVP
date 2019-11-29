package com.omerilhanli.newsapimvp.mvvm.model

import com.omerilhanli.newsapimvp.api.Api
import com.omerilhanli.newsapimvp.api.constant.ApiConst
import com.omerilhanli.newsapimvp.asistant.extension.dateFormat
import com.omerilhanli.newsapimvp.asistant.extension.getBefore10DayFromToday
import com.omerilhanli.newsapimvp.mvvm.contract.NewsContract
import com.omerilhanli.newsapimvp.mvvm.model.entity.News
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsModel @Inject constructor(val api: Api, val apiKey: String) : NewsContract.Model {

    override var textQuery: String = ""

    override var selectedDate: String = ""
        get() {
            return if (field.isEmpty()) {
                // 10 day ago from today \\
                getDateFrom10DayAgo()?.dateFormat(fromApi = false, forApi = true)!!
            } else {
                // OR selected day \\
                field
            }
        }

    override fun getNews(): Observable<News>? {
        return api
            .getSelectedDateNews(textQuery, selectedDate, ApiConst.VALUE_SORT_BY, apiKey)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    override fun getDateFrom10DayAgo(): Date? {
        val calendar = Calendar.getInstance()
        return calendar.getBefore10DayFromToday()
    }
}