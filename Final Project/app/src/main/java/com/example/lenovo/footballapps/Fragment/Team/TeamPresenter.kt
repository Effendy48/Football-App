package com.example.lenovo.footballapps.Fragment.Team

import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Api.TheSportDbApi
import com.example.lenovo.footballapps.Model.League.LeagueResponse
import com.example.lenovo.footballapps.Model.Team.TeamResponse
import com.example.lenovo.footballapps.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(private val view: TeamView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamsLeague(id: String) {
        view.showLoading()
        async(context.main) {
            val dataTeams = bg {
                gson.fromJson(apiRepository.
                        doRequest(TheSportDbApi.getTeamsLeague(id)),
                        TeamResponse::class.java)
            }
            view.showTeamList(dataTeams.await().teams)
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