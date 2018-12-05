package com.example.lenovo.footballapps.Adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import com.example.lenovo.footballapps.Model.Match.Match
import com.example.lenovo.footballapps.R
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter (private var match : MutableList<Match>, var listener: (Match) ->
Unit): RecyclerView.Adapter<MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_match, parent, false))

    }

    override fun getItemCount(): Int {
        return match.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(match[position], listener)
    }
}

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val dateMatch: TextView = view.findViewById(R.id.tv_date_match)
    private val teamHome: TextView = view.findViewById(R.id.home_Team)
    private val teamAway: TextView = view.findViewById(R.id.away_team)
    private val CvMatch: CardView = view.findViewById(R.id.cv_item_match)
    private val teamHomeScore: TextView = view.findViewById(R.id.home_Team_score)
    private val teamAwayScore: TextView = view.findViewById(R.id.away_team_score)
    private val matchTime : TextView = view.findViewById(R.id.tv_time_match)

    fun bindItem(match: Match, listener: (Match) -> Unit) {
        teamHome.text = match.strHomeTeam
        teamAway.text = match.strAwayTeam
        dateMatch.text = match.dateEvent
        teamHomeScore.text = match.intHomeScore
        teamAwayScore.text = match.intAwayScore

        val FormatParse = SimpleDateFormat("HH:mm:ssZ")
        val date : Date = FormatParse.parse(match.strTime.toString())

        matchTime.text = SimpleDateFormat("HH:mm").format(date)


        if (match.intHomeScore == null) teamHomeScore.text = "-"
        else teamHomeScore.text = match.intHomeScore
        if (match.intAwayScore == null) teamAwayScore.text = "-"
        else teamAwayScore.text = match.intAwayScore

        itemView.setOnClickListener {
            listener(match)
        }
    }
}