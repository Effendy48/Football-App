package com.example.lenovo.footballapps.Fragment.DetailMatchFavorite

import com.example.lenovo.footballapps.Model.Team.TeamData

interface DetailMatchFavoriteView {

    fun showImageHomeTeam(data : List<TeamData>)
    fun showImageAwayTeam(data : List<TeamData>)
}