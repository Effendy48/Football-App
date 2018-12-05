package com.example.lenovo.footballapps.Fragment.Player

import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Api.TheSportDbApi
import com.example.lenovo.footballapps.Model.Player.PlayerResponse
import com.example.lenovo.footballapps.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerTeamPresenter (private val view : PlayerTeamView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context : CoroutineContextProvider = CoroutineContextProvider()){
    fun getPlayerTeam(id : String){
        view.showLoading()
        async(context.main){
            val dataPlayer = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getPlayerTeam(id)),
                        PlayerResponse::class.java)
            }
            view.showPlayerList(dataPlayer.await().players)
            view.hideLoading()
        }
    }
}