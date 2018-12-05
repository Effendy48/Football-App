package com.example.lenovo.footballapps.Fragment.DetailTeamFavorite

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.example.lenovo.footballapps.Db.database
import com.example.lenovo.footballapps.Fragment.DetailTeam.DetailTeam
import com.example.lenovo.footballapps.Fragment.Player.PlayerTeam
import com.example.lenovo.footballapps.Model.Favorite.FavoriteTeam
import com.example.lenovo.footballapps.Model.Team.TeamData
import com.example.lenovo.footballapps.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DetailTeamFavorite : AppCompatActivity() {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var id_Team: String

    companion object {
        val POSITION_EXTRA = "position_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team_favorite)

        val detail = intent.getParcelableExtra<FavoriteTeam>(POSITION_EXTRA)
        id_Team = detail.IdTeam.toString()

        getSupportActionBar()?.setTitle(detail.strTeam)

        Picasso.get().load(detail.strTeamBanner).into(img_detail_team_favorite)
        tv_detail_team_favorite.text = detail.strDescriptionEN

        iv_player_team_favorite.setOnClickListener {
            startActivity<PlayerTeam>("id_team" to id_Team)
        }

        favoriteState()
    }

    private fun setFavorite(){
        if(isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat
                    .getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat
                    .getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(ID_TEAM = {id_team})", "id_team" to id_Team)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(ID_TEAM = {id_Team})", "id_Team" to id_Team)
            }
            toast("Remove to Favorite").show()
        }catch (e : SQLiteConstraintException){
            toast(e.localizedMessage).show()

        }
    }
    private fun addToFavorite(){
        val detail = intent.getParcelableExtra<TeamData>(DetailTeam.POSITION_EXTRA)

        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.ID_TEAM to detail.idTeam.toString(),
                        FavoriteTeam.STR_TEAM to detail.strTeam.toString(),
                        FavoriteTeam.STADIUM to detail.strStadium.toString(),
                        FavoriteTeam.DESCRIPTION to detail.strDescriptionEN.toString(),
                        FavoriteTeam.TEAM_BADGE to detail.strTeamBadge.toString(),
                        FavoriteTeam.TEAM_BANNER to detail.strTeamBanner.toString(),
                        FavoriteTeam.TEAM_LOGO to detail.strTeamLogo.toString()
                )
            }
            toast("Added to Favorite").show()
        }catch (e : SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean{
        return when (item.itemId){
            R.id.add_to_favorite ->{
                if(isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
