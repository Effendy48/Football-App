package com.example.lenovo.footballapps.Fragment.DetailMatchFavorite

import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Api.TheSportDbApi
import com.example.lenovo.footballapps.Fragment.DetailMatch.DetailMatchView
import com.example.lenovo.footballapps.Model.Team.TeamResponse
import com.example.lenovo.footballapps.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailMatchFavoritePresenter(private val view : DetailMatchFavoriteView,
                                   private val apiRepository: ApiRepository,
                                   private val gson: Gson,
                                   private val context : CoroutineContextProvider = CoroutineContextProvider()) {
    fun getTeamHome(id : String){

        async(context.main){
            val dataHomeTeam = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getDetailTeam(id)), TeamResponse::class.java)
            }
            view.showImageHomeTeam(dataHomeTeam.await().teams!!)

        }
    }
    fun getTeamAway(id : String){

        async(context.main){
            val dataAwayTeam = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getDetailTeam(id)), TeamResponse::class.java)
            }
            view.showImageAwayTeam(dataAwayTeam.await().teams!!)

        }
    }

}