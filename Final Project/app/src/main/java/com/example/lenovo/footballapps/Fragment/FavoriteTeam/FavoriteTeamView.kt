package com.example.lenovo.footballapps.Fragment.FavoriteTeam

import com.example.lenovo.footballapps.Model.Favorite.FavoriteTeam

interface FavoriteTeamView {
    fun showFavoriteTeamList(data : List<FavoriteTeam>)
}