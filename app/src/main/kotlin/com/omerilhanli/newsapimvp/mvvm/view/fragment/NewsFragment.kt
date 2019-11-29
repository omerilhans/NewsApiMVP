package com.omerilhanli.newsapimvp.mvvm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.omerilhanli.newsapimvp.R
import com.omerilhanli.newsapimvp.asistant.TEXT_DEFAULT_SEARCH
import com.omerilhanli.newsapimvp.asistant.callback.ItemClickListener
import com.omerilhanli.newsapimvp.asistant.callback.DateSelection
import com.omerilhanli.newsapimvp.asistant.extension.*
import com.omerilhanli.newsapimvp.databinding.FragmentNewsBinding
import com.omerilhanli.newsapimvp.mvvm.contract.NewsContract
import com.omerilhanli.newsapimvp.mvvm.model.entity.News
import com.omerilhanli.newsapimvp.mvvm.view.adapter.ArticleAdapter
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsFragment
@Inject constructor(
    var presenter: NewsContract.Presenter,
    var adapter: ArticleAdapter
) : Fragment(),
    NewsContract.View, DateSelection {

    //region Properties
    private lateinit var binding: FragmentNewsBinding

    private val itemClickListener = object :
        ItemClickListener {
        override fun click(url: String) {
            context?.openWebTabBrowser(url) // just open
        }
    }

    private val queryTextChangeListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            presenter.setTextQuery(query.trim())
            return false
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            return false
        }
    }
    //endregion

    //region Fragment Default
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.view = this
        presenter.isNetworkStatus = ::handleNetworkConnection
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepare()
        search()
    }
    //endregion

    //region News - View
    override fun onDateSelect(date: Date?) {
        presenter.setDate(date)
    }

    override fun displayNews(news: News?) {
        binding.articles = news?.articles.sortForDate()
        binding.searchView.hideKeyboard() // logic
    }

    override fun openUrlOnWebTabBrowser(url: String) {
        context?.openWebTabBrowser(url)
    }

    override fun handleError(msg: String) {
        context!!.showPopupFor(msg)
        binding.tvEmptyMessage.text = msg
    }
    //endregion

    //region Private
    private fun prepare() {
        binding.listVisibility = presenter.visibilityList
        binding.progressVisibility = presenter.visibilityProgress
        binding.adapter = adapter
        binding.listener = itemClickListener

        binding.searchView.setOnQueryTextListener(queryTextChangeListener)
        binding.searchView.isIconifiedByDefault = false
    }

    private fun search() {
        binding.searchView.setQuery(TEXT_DEFAULT_SEARCH, true)
    }

    private fun handleNetworkConnection(): Boolean {
        return context?.hasNetworkConnection()!!
    }
    //endregion
}