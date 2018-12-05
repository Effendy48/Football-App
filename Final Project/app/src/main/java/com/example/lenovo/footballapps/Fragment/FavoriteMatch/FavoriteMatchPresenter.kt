package com.example.lenovo.footballapps.Fragment.FavoriteMatch

import android.content.Context
import com.example.lenovo.footballapps.Db.database
import com.example.lenovo.footballapps.Model.Favorite.FavoriteMatch
import com.example.lenovo.footballapps.Model.Favorite.FavoriteTeam
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchPresenter (private val view : FavoriteMatchView,
                              private val context: Context) {
    fun getFavoriteMatch(){
        context?.database?.use{
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favoriteMatch = result.parseList(classParser<FavoriteMatch>())
            view.showFavoriteMatch(favoriteMatch)
        }
    }
}