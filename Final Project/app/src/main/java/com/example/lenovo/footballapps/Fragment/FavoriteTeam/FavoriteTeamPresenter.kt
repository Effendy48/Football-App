package com.example.lenovo.footballapps.Fragment.FavoriteTeam

import android.content.Context
import com.example.lenovo.footballapps.Db.database
import com.example.lenovo.footballapps.Model.Favorite.FavoriteTeam
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamPresenter(private val view : FavoriteTeamView,
                            private val context: Context) {
    fun getFavoriteTeam(){
        context?.database?.use{
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favoriteTeam = result.parseList(classParser<FavoriteTeam>())
           view.showFavoriteTeamList(favoriteTeam)
        }
    }
}