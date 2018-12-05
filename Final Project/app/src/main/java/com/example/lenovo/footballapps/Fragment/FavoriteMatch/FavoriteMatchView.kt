package com.example.lenovo.footballapps.Fragment.FavoriteMatch

import com.example.lenovo.footballapps.Model.Favorite.FavoriteMatch

interface FavoriteMatchView {
    fun showFavoriteMatch(data : List<FavoriteMatch>)
}