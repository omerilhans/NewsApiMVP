package com.omerilhanli.newsapimvp.mvvm.presenter

import com.omerilhanli.newsapimvp.mvvm.contract.MainContract
import com.omerilhanli.newsapimvp.mvvm.view.fragment.NewsFragment
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainPresenter @Inject constructor(
    val model: MainContract.Model,
    override var fragment: NewsFragment
) : MainContract.Presenter {

    override var view: MainContract.View? = null

    override fun prepareAndGetSelectedDate(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val date = model.setSelectedDateThenGet(year, monthOfYear, dayOfMonth)
        view?.sendDateToAttachedFragment(date)
    }

    override fun getOpenDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val monthOfYear = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        view?.openDatePicker(year, monthOfYear, dayOfMonth)
    }
}