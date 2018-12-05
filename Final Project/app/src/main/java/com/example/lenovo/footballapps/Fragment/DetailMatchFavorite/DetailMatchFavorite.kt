package com.example.lenovo.footballapps.Fragment.DetailMatchFavorite

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Menu
import android.view.MenuItem
import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Db.database
import com.example.lenovo.footballapps.Fragment.DetailMatch.DetailMatch
import com.example.lenovo.footballapps.Fragment.DetailMatch.DetailMatchPresenter
import com.example.lenovo.footballapps.Model.Favorite.FavoriteMatch
import com.example.lenovo.footballapps.Model.Match.Match
import com.example.lenovo.footballapps.Model.Team.TeamData
import com.example.lenovo.footballapps.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailMatchFavorite : AppCompatActivity(),DetailMatchFavoriteView {
    override fun showImageHomeTeam(data: List<TeamData>) {
        for (item: TeamData in data.iterator()) {
            Picasso.get().load(item.strTeamBadge).into(img_away_detail_match_favorite)
        }
    }

    override fun showImageAwayTeam(data: List<TeamData>) {
        for (item: TeamData in data.iterator()) {
            Picasso.get().load(item.strTeamBadge).into(img_home_detail_match_favorite)
        }
    }

    private lateinit var presenter : DetailMatchFavoritePresenter
    private lateinit var idHome: String
    private lateinit var idAway: String
    private lateinit var goalAway: String
    private lateinit var goalHome: String
    private lateinit var defenceAway: String
    private lateinit var defenceHome: String
    private lateinit var midfieldAway: String
    private lateinit var midfieldHome: String
    private lateinit var forwardHome: String
    private lateinit var forwardAway: String
    private lateinit var subtitutiesHome: String
    private lateinit var subtitutiesAway: String

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id_Event: String

    companion object {
    val POSITION_EXTRA = "position_extra"
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match_favorite)

        val detail = intent.getParcelableExtra<FavoriteMatch>(DetailMatchFavorite.POSITION_EXTRA)

        presenter = DetailMatchFavoritePresenter(this, ApiRepository(), Gson())

        presenter.getTeamHome(detail.teamHomeId.toString())
        presenter.getTeamAway(detail.teamAwayId.toString())

        Detail()
        favoriteState()

    }

    private fun Detail() {
        val detail = intent.getParcelableExtra<FavoriteMatch>(DetailMatchFavorite.POSITION_EXTRA)
        idHome = detail.teamHomeId.toString()
        idAway = detail.teamAwayId.toString()
        id_Event = detail.idEvent.toString()

/*
        tv_away_score_favorite.text = detail.scoreTeamAway
        tv_home_score_favorite.text = detail.scoreTeamHome

        tv_goals_away_favorite.text = detail.teamAwayGoals
        tv_goals_home_favorite.text = detail.teamHomeGoals
*/
        tv_date_detail_match_favorite.text = detail.dateEvent


        goalAway = detail.teamAwayGoals.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", "; \n").trim()
                .replace(",", ";").trim()

        goalHome = detail.teamHomeGoals.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", "; \n").trim()
                .replace(",", ";").trim()
        defenceAway = detail.teamAwayDefence.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", " \n").trim()
                .replace(",", ".").trim()
        defenceHome = detail.teamHomeDefence.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()
        midfieldAway = detail.teamAwayMidfield.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()
        midfieldHome = detail.teamHomeMidfield.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()
        forwardAway = detail.teamAwayForward.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()
        forwardHome = detail.teamHomeForward.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()

        subtitutiesAway = detail.teamAwaySubtitutes.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()
        subtitutiesHome = detail.teamHomeSubtitutes.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()


        if (detail.teamHomeDefence != "null") tv_defence_home_match_favorite.text = defenceHome.trim() else tv_defence_home_match_favorite.text = "Tidak Ada"
        if (detail.teamAwayDefence != "null") tv_defence_away_match_favorite.text = defenceAway.trim() else tv_defence_away_match_favorite.text = "Tidak Ada"

        if (detail.teamAwayMidfield != "null") tv_midfield_away_match_favorite.text = midfieldAway.trim() else tv_midfield_away_match_favorite.text = "Tidak Ada"
        if (detail.teamHomeMidfield != "null") tv_midfield_home_match_favorite.text = midfieldHome.trim() else tv_midfield_home_match_favorite.text = "Tidak Ada"

        if (detail.teamAwayForward != "null") tv_forward_away_match_favorite.text = forwardAway.trim() else tv_forward_away_match_favorite.text = "Tidak Ada"
        if (detail.teamHomeForward != "null") tv_forward_home_match_favorite.text = forwardHome.trim() else tv_forward_home_match_favorite.text = "Tidak Ada"

        if (detail.teamHomeGoals == "null") tv_goals_home_match_favorite.text = "Tidak Ada" else tv_goals_home_match_favorite.text = goalHome.trim()
        if (detail.teamAwayGoals == "null") tv_goals_away_match_favorite.text = "Tidak Ada" else tv_goals_away_match_favorite.text = goalAway.trim()

        if (detail.scoreTeamAway == "null") tv_away_score_match_favorite.text = "-" else tv_away_score_match_favorite.text = detail.scoreTeamAway.toString()
        if (detail.scoreTeamHome == "null") tv_home_score_match_favorite.text = "-" else tv_home_score_match_favorite.text = detail.scoreTeamHome.toString()

        if (detail.teamAwayGK == "null")tv_goalkeeper_away_match_favorite.text = "Tidak Ada" else tv_goalkeeper_away_match_favorite.text = detail.teamAwayGK.toString()
        if (detail.teamHomeGK == "null")tv_goalkeeper_home_match_favorite.text = "Tidak Ada" else tv_goalkeeper_home_match_favorite.text = detail.teamHomeGK.toString()

        if (detail.teamAwaySubtitutes != "null" || detail.teamAwaySubtitutes == "")
            tv_subtitutes_away_match_favorite.text = subtitutiesAway.trim()
        else tv_subtitutes_away_match_favorite.text = "Tidak Ada"

        if (detail.teamHomeSubtitutes != "null" || detail.teamHomeSubtitutes == "")
            tv_subtitutes_home_match_favorite.text = subtitutiesHome.trim()
        else tv_subtitutes_home_match_favorite.text = "Tidak Ada"


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
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs("(ID_EVENT = {id})", "id" to id_Event)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
    private fun addToFavorite(){
        val detail = intent.getParcelableExtra<Match>(DetailMatch.POSITION_EXTRA)

        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.ID_EVENT to detail.idEvent.toString(),
                        FavoriteMatch.TEAM_AWAY_ID to detail.idAwayTeam.toString(),
                        FavoriteMatch.TEAM_AWAY_SCORE to detail.intAwayScore.toString(),


                        FavoriteMatch.TEAM_HOME_NAME to detail.strHomeTeam.toString(),
                        FavoriteMatch.TEAM_AWAY_NAME to detail.strAwayTeam.toString(),

                        FavoriteMatch.TEAM_HOME_GOALS to goalHome.trim(),
                        FavoriteMatch.TEAM_AWAY_GOALS to goalAway.trim(),

                        FavoriteMatch.TEAM_HOME_GK to detail.strHomeLineupGoalkeeper.toString(),
                        FavoriteMatch.TEAM_AWAY_GK to detail.strAwayLineupGoalkeeper.toString(),

                        FavoriteMatch.TEAM_HOME_DEFENCE to defenceHome.trim(),
                        FavoriteMatch.TEAM_AWAY_DEFENCE to defenceAway.trim(),

                        FavoriteMatch.TEAM_HOME_MIDFIELD to midfieldHome.trim(),
                        FavoriteMatch.TEAM_AWAY_MIDFIELD to midfieldAway.trim(),

                        FavoriteMatch.TEAM_HOME_FORWARD to forwardHome.trim(),
                        FavoriteMatch.TEAM_AWAY_FORWARD to forwardAway.trim(),

                        FavoriteMatch.TEAM_HOME_SUBTITUTES to subtitutiesHome.trim(),
                        FavoriteMatch.TEAM_AWAY_SUBTITUTES to subtitutiesAway.trim(),


                        FavoriteMatch.TEAM_HOME_ID to detail.idHomeTeam.toString(),
                        FavoriteMatch.TEAM_HOME_SCORE to detail.intHomeScore.toString(),
                        FavoriteMatch.DATE_EVENT to detail.dateEvent.toString()

                )
            }
            toast("Added to Favorite").show()
        }catch (e : SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }
    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(ID_EVENT = {idEvent})", "idEvent" to id_Event)
            }
            toast("Remove to Favorite").show()
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
