package com.example.lenovo.footballapps.Fragment.DetailMatch

import com.example.lenovo.footballapps.Model.Team.TeamData

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showImageHomeTeam(data : List<TeamData>)
    fun showImageAwayTeam(data : List<TeamData>)
}