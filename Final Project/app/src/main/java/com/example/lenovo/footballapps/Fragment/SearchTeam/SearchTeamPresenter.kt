package com.example.lenovo.footballapps.Fragment.SearchTeam

import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Api.TheSportDbApi
import com.example.lenovo.footballapps.Fragment.Player.PlayerTeamView
import com.example.lenovo.footballapps.Model.SearchTeam.SearchTeamResponse
import com.example.lenovo.footballapps.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchTeamPresenter(private val view : SearchTeamView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context : CoroutineContextProvider = CoroutineContextProvider()) {
    fun getSearchTeam(text : String){
        view.showLoading()
        async(context.main){
            val dataSearchTeam = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getSearchTeams(text)), SearchTeamResponse::class.java)
            }
            view.showSearchTeamList(dataSearchTeam.await().teams)
            view.hideLoading()
        }
    }
}