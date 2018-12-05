package com.example.lenovo.footballapps.Fragment.SearchMatch

import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Api.TheSportDbApi
import com.example.lenovo.footballapps.Model.SearchMatch.SearchMatchResponse
import com.example.lenovo.footballapps.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(private val view : SearchMatchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context : CoroutineContextProvider = CoroutineContextProvider()) {
    fun getSearchMatch(text : String){
        view.showLoading()
        async(context.main){
            val dataSearchMatch = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getSearchMatch(text)), SearchMatchResponse::class.java)

            }
            view.showSearchMatchList(dataSearchMatch.await().resultSearchMatch)
            view.hideLoading()
        }
    }
}