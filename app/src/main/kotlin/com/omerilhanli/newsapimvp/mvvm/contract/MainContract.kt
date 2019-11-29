package com.omerilhanli.newsapimvp.mvvm.contract

import com.omerilhanli.newsapimvp.mvvm.view.fragment.NewsFragment
import java.util.*

interface MainContract {

    interface Model {
        fun setSelectedDateThenGet(year: Int, monthOfYear: Int, dayOfMonth: Int): Date
    }

    interface View {
        fun sendDateToAttachedFragment(date: Date?)
        fun openDatePicker(year: Int, monthOfYear: Int, dayOfMonth: Int)
    }

    interface Presenter : BaseContract.BasePresenter<View> {
        var fragment: NewsFragment
        fun prepareAndGetSelectedDate(year: Int, monthOfYear: Int, dayOfMonth: Int)
        fun getOpenDatePicker()
    }
}