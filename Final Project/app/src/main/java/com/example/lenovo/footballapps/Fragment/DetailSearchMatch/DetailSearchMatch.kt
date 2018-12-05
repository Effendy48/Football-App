package com.example.lenovo.footballapps.Fragment.DetailSearchMatch

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Db.database
import com.example.lenovo.footballapps.Fragment.DetailMatch.DetailMatchPresenter
import com.example.lenovo.footballapps.Fragment.DetailMatch.DetailMatchView
import com.example.lenovo.footballapps.Model.Favorite.FavoriteMatch
import com.example.lenovo.footballapps.Model.Match.Match
import com.example.lenovo.footballapps.Model.SearchMatch.SearchMatch
import com.example.lenovo.footballapps.Model.Team.TeamData
import com.example.lenovo.footballapps.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_search_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailSearchMatch : AppCompatActivity(), DetailSearchMatchView {
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showImageHomeTeam(data: List<TeamData>) {
        for (item: TeamData in data.iterator()) {
            Picasso.get().load(item.strTeamBadge).into(img_home_detail_search_match)
        }
    }

    override fun showImageAwayTeam(data: List<TeamData>) {
        for (item: TeamData in data.iterator()) {
            Picasso.get().load(item.strTeamBadge).into(img_away_detail_search_match)
        }
    }

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

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var detailSearchMatchPresenter: DetailSearchMatchPresenter

    private lateinit var id_Event: String

    companion object {
        val POSITION_EXTRA = "position_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_search_match)

        val detail = intent.getParcelableExtra<SearchMatch>(POSITION_EXTRA)

        detailSearchMatchPresenter = DetailSearchMatchPresenter(this, ApiRepository(), Gson())

        detailSearchMatchPresenter.getTeamHome(detail.idHomeTeam.toString())
        detailSearchMatchPresenter.getTeamAway(detail.idAwayTeam.toString())

        DetailMatch()
        favoriteState()
    }
    private fun DetailMatch() {
        val detail = intent.getParcelableExtra<SearchMatch>(POSITION_EXTRA)
        idHome = detail.idHomeTeam.toString()
        idAway = detail.idAwayTeam.toString()

        id_Event = detail.idEvent.toString()


        goalAway = detail.strAwayGoalDetails.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", "; \n").trim()
                .replace(",", ";").trim()

        goalHome = detail.strHomeGoalDetails.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", "; \n").trim()
                .replace(",", ";").trim()
        defenceAway = detail.strAwayLineupDefense.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", " \n").trim()
                .replace(",", ".").trim()
        defenceHome = detail.strHomeLineupDefense.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()
        midfieldAway = detail.strAwayLineupMidfield.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()
        midfieldHome = detail.strHomeLineupMidfield.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()
        forwardAway = detail.strAwayLineupForward.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()
        forwardHome = detail.strHomeLineupForward.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()

        subtitutiesAway = detail.strAwayLineupSubstitutes.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()
        subtitutiesHome = detail.strHomeLineupSubstitutes.toString().split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ". \n").trim()
                .replace(",", ".").trim()

        tv_date_detail_search_match.text = detail.dateEvent.toString()

        if (detail.strHomeLineupDefense != null) tv_defence_home.text = defenceHome.trim() else tv_defence_home.text = "Tidak Ada"
        if (detail.strAwayLineupDefense != null) tv_defence_away.text = defenceAway.trim() else tv_defence_away.text = "Tidak Ada"

        if (detail.strAwayLineupMidfield != null) tv_midfield_away.text = midfieldAway.trim() else tv_midfield_away.text = "Tidak Ada"
        if (detail.strHomeLineupMidfield != null) tv_midfield_home.text = midfieldHome.trim() else tv_midfield_home.text = "Tidak Ada"

        if (detail.strAwayLineupForward != null) tv_forward_away.text = forwardAway.trim() else tv_forward_away.text = "Tidak Ada"
        if (detail.strHomeLineupForward != null) tv_forward_home.text = forwardHome.trim() else tv_forward_home.text = "Tidak Ada"

        if (detail.strHomeGoalDetails == null) tv_goals_home.text = "Tidak Ada" else tv_goals_home.text = goalHome.trim()
        if (detail.strAwayGoalDetails == null) tv_goals_away.text = "Tidak Ada" else tv_goals_away.text = goalAway.trim()

        if (detail.intAwayScore == null) tv_away_score.text = "-" else tv_away_score.text = detail.intAwayScore.toString()
        if (detail.intHomeScore == null) tv_home_score.text = "-" else tv_home_score.text = detail.intHomeScore.toString()

        if (detail.strAwayLineupGoalkeeper != null) tv_goalkeeper_away.text = detail.strAwayLineupGoalkeeper.toString() else tv_goalkeeper_away.text = "Tidak Ada"
        if (detail.strHomeLineupGoalkeeper != null) tv_goalkeeper_home.text = detail.strHomeLineupGoalkeeper.toString() else tv_goalkeeper_home.text = "Tidak Ada"

        if (detail.strAwayLineupSubstitutes != null || detail.strAwayLineupSubstitutes == "")
            tv_subtitutes_away.text = subtitutiesAway.trim()
        else tv_subtitutes_away.text = "Tidak Ada"

        if (detail.strHomeLineupSubstitutes != null || detail.strHomeLineupSubstitutes == "")
            tv_subtitutes_home.text = subtitutiesHome.trim()
        else tv_subtitutes_home.text = "Tidak Ada"
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
        val detail = intent.getParcelableExtra<SearchMatch>(com.example.lenovo.footballapps.Fragment.DetailMatch.DetailMatch.POSITION_EXTRA)

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
