package com.omerilhanli.newsapimvp.mvvm.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.omerilhanli.newsapimvp.asistant.extension.inject
import com.omerilhanli.newsapimvp.mvvm.contract.MainContract
import javax.inject.Inject
import android.view.Menu
import android.view.MenuItem
import android.app.DatePickerDialog
import androidx.databinding.DataBindingUtil
import com.omerilhanli.newsapimvp.R
import com.omerilhanli.newsapimvp.asistant.URL_GITHUB
import com.omerilhanli.newsapimvp.asistant.callback.DateSelection
import com.omerilhanli.newsapimvp.asistant.extension.configure
import com.omerilhanli.newsapimvp.asistant.extension.openWebTabBrowser
import com.omerilhanli.newsapimvp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View {

    //region Properties
    @Inject
    lateinit var presenter: MainContract.Presenter

    private val dateSetListener: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            presenter.prepareAndGetSelectedDate(year, monthOfYear, dayOfMonth)
        }
    //endregion

    //region Activity Default
    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        supportActionBar?.configure()

        presenter.view = this

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.fragment = presenter.fragment
        binding.fragmentManager = supportFragmentManager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_news, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> presenter.getOpenDatePicker()
            android.R.id.home -> this.openWebTabBrowser(URL_GITHUB)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun openDatePicker(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        DatePickerDialog(this, dateSetListener, year, monthOfYear, dayOfMonth)
            .show()
    }
    //endregion

    //region Main - View
    override fun sendDateToAttachedFragment(date: Date?) {
        (presenter.fragment as DateSelection).onDateSelect(date)
    }
    //endregion
}
