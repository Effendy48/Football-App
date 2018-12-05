package com.example.lenovo.footballapps.Adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lenovo.footballapps.Model.Favorite.FavoriteMatch
import com.example.lenovo.footballapps.R
import java.text.SimpleDateFormat
import java.util.*

class FavoriteMatchAdapter (private var favoriteMatch : MutableList<FavoriteMatch>,
                            var listener : (FavoriteMatch) -> Unit):
                            RecyclerView.Adapter<FavoriteMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent,false))
    }

    override fun getItemCount(): Int {
       return favoriteMatch.size
    }

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(favoriteMatch[position], listener)
    }

}

class FavoriteMatchViewHolder (view : View) : RecyclerView.ViewHolder(view) {
    private val dateMatch: TextView = view.findViewById(R.id.tv_date_match)
    private val teamHome: TextView = view.findViewById(R.id.home_Team)
    private val teamAway: TextView = view.findViewById(R.id.away_team)
    private val CvMatch: CardView = view.findViewById(R.id.cv_item_match)
    private val teamHomeScore: TextView = view.findViewById(R.id.home_Team_score)
    private val teamAwayScore: TextView = view.findViewById(R.id.away_team_score)
    private val matchTime : TextView = view.findViewById(R.id.tv_time_match)

    fun bindItem(favoriteMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit){
        teamHome.text = favoriteMatch.teamHomeName
        teamAway.text = favoriteMatch.teamAwayName
        dateMatch.text = favoriteMatch.dateEvent
        teamHomeScore.text = favoriteMatch.scoreTeamHome
        teamAwayScore.text = favoriteMatch.scoreTeamAway



        if (favoriteMatch.scoreTeamHome == "null") teamHomeScore.text = "-"
        else teamHomeScore.text = favoriteMatch.scoreTeamHome
        if (favoriteMatch.scoreTeamAway == "null") teamAwayScore.text = "-"
        else teamAwayScore.text = favoriteMatch.scoreTeamAway

        itemView.setOnClickListener {
            listener(favoriteMatch)
        }
    }

}
