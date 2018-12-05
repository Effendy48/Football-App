package com.example.lenovo.footballapps.Fragment.NextMatch

import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Api.TheSportDbApi
import com.example.lenovo.footballapps.Model.League.LeagueResponse
import com.example.lenovo.footballapps.Model.Match.MatchResponse
import com.example.lenovo.footballapps.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.coroutineContext

class NextMatchPresenter (private val view : NextMatchView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context : CoroutineContextProvider = CoroutineContextProvider()) {
    fun getNextMatch(id : String){
        view.showLoading()
        async(context.main){
            val dataNextMatch = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getNextMatch(id)),
                        MatchResponse::class.java)
            }
            view.showNextMatchList(dataNextMatch.await().matchs)
            view.hideLoading()
        }
    }
    fun getAllLeagues(){
        view.showLoading()
        async(context.main){
            val dataLeague = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getAllLeague()), LeagueResponse::class.java)

            }
            view.showLeagueMatchList(dataLeague.await().leagues)
        }
    }
}