package com.example.lenovo.footballapps.Fragment.Team

import com.example.lenovo.footballapps.Model.League.League
import com.example.lenovo.footballapps.Model.Team.TeamData

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data : List<TeamData>)
    fun showLeagueList(data : List<League>)
}