package com.example.lenovo.footballapps.Fragment.DetailSearchMatch

import com.example.lenovo.footballapps.Model.Team.TeamData

interface DetailSearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showImageHomeTeam(data : List<TeamData>)
    fun showImageAwayTeam(data : List<TeamData>)
}