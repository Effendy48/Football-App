package com.example.lenovo.footballapps.Fragment.PastMatch

import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Api.TheSportDbApi
import com.example.lenovo.footballapps.Fragment.NextMatch.NextMatchView
import com.example.lenovo.footballapps.Model.League.LeagueResponse
import com.example.lenovo.footballapps.Model.Match.MatchResponse
import com.example.lenovo.footballapps.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PastMatchPresenter (private val view : PastMatchView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context : CoroutineContextProvider = CoroutineContextProvider()) {
    fun getPastMatch(id: String) {
        view.showLoading()
        async(context.main) {
            val dataPastMatch = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getPastMatch(id)),
                        MatchResponse::class.java)
            }
            view.showPastMatchList(dataPastMatch.await().matchs)
            view.hideLoading()
        }
    }

    fun getAllLeagues() {
        view.showLoading()
        async(context.main) {
            val dataLeague = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getAllLeague()), LeagueResponse::class.java)

            }
            view.showLeagueList(dataLeague.await().leagues)
        }
    }
}