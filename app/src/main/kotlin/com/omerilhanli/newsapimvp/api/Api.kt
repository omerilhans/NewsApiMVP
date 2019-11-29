package com.omerilhanli.newsapimvp.api

import com.omerilhanli.newsapimvp.api.constant.ApiConst
import com.omerilhanli.newsapimvp.api.constant.ApiEnd
import com.omerilhanli.newsapimvp.mvvm.model.entity.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(ApiEnd.END_NEWS)
    fun getSelectedDateNews(
        @Query(ApiConst.KEY_QUERY) query: String,
        @Query(ApiConst.KEY_FROM) from: String,
        @Query(ApiConst.KEY_SORT_BY) sortBy: String,
        @Query(ApiConst.KEY_API) apiKey: String
    ): Observable<News>?

}