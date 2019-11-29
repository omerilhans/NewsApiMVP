package com.omerilhanli.newsapimvp.mvvm.presenter

import androidx.databinding.BaseObservable
import com.omerilhanli.newsapimvp.asistant.*
import com.omerilhanli.newsapimvp.asistant.extension.dateFormat
import com.omerilhanli.newsapimvp.mvvm.model.entity.ViewVisibility
import com.omerilhanli.newsapimvp.mvvm.contract.NewsContract
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsPresenter @Inject constructor(
    val model: NewsContract.Model,
    _progressVisibility: ViewVisibility,
    _listVisibility: ViewVisibility
) : BaseObservable(), NewsContract.Presenter {

    override var view: NewsContract.View? = null
    override val visibilityProgress = _progressVisibility
    override val visibilityList = _listVisibility
    override var isNetworkStatus: (() -> Boolean)? = null

    override fun setTextQuery(query: String) {
        if (query.isNotEmpty() && query.length >= 2) {
            model.textQuery = query
            triggerRequest()
        } else {
            prepareErrorCase(TEXT_ERR_VALID_QUERY)
        }
    }

    override fun setDate(date: Date?) {
        model.selectedDate = date?.dateFormat(fromApi = false, forApi = true)!!
        triggerRequest()
    }

    override fun fetchNews() {
        prepareVisibility(true, visibilityList, visibilityProgress)

        val _disp_ =
            model.getNews()
                ?.subscribe({ news ->

                    if (news.articles?.isEmpty() == true) {
                        prepareErrorCase(TEXT_ERR_NOT_FOUND + model.selectedDate)
                    } else {
                        prepareVisibility(false, visibilityProgress)
                    }

                    view?.displayNews(news) // ***

                }, {
                    prepareErrorCase(TEXT_ERR_NOT_FOUND + model.selectedDate)
                })
    }

    override fun openUrlOnWebTabBrowser(url: String) {
        if (isNetworkStatus?.invoke() == true) {
            view?.openUrlOnWebTabBrowser(url)
        } else {
            prepareErrorCase(TEXT_ERR_NO_NETWORK)
        }
    }

    private fun triggerRequest() {
        if (isNetworkStatus?.invoke() == true) {
            fetchNews()
        } else {
            prepareErrorCase(TEXT_ERR_NO_NETWORK)
        }
    }

    private fun prepareErrorCase(errorMessage: String) {
        prepareVisibility(false, visibilityList, visibilityProgress)
        view?.handleError(errorMessage)
    }

    private fun prepareVisibility(visible: Boolean, vararg properties: ViewVisibility) {
        properties.forEach {
            it.visible = visible
        }
    }
}