package com.omerilhanli.newsapimvp.mvvm.model

import com.omerilhanli.newsapimvp.asistant.extension.date
import com.omerilhanli.newsapimvp.mvvm.contract.MainContract
import javax.inject.Inject
import javax.inject.Singleton
import java.util.*

@Singleton
class MainModel @Inject constructor() : MainContract.Model {

    override fun setSelectedDateThenGet(year: Int, monthOfYear: Int, dayOfMonth: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        return calendar.date()
    }
}