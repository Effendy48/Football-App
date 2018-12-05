package com.example.lenovo.footballapps.Adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lenovo.footballapps.Model.SearchMatch.SearchMatch
import com.example.lenovo.footballapps.R
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class SearchMatchAdapter(private var searchMatch : MutableList<SearchMatch>, var listener : (SearchMatch) -> Unit): RecyclerView.Adapter<SearchMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMatchViewHolder {
        return SearchMatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int {
        return searchMatch.size
    }

    override fun onBindViewHolder(holder: SearchMatchViewHolder, position: Int) {
        holder.bindItem(searchMatch[position], listener)
    }
}

class SearchMatchViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val dateMatch: TextView = view.findViewById(R.id.tv_date_match)
    private val teamHome: TextView = view.findViewById(R.id.home_Team)
    private val teamAway: TextView = view.findViewById(R.id.away_team)
    private val CvMatch: CardView = view.findViewById(R.id.cv_item_match)
    private val teamHomeScore: TextView = view.findViewById(R.id.home_Team_score)
    private val teamAwayScore: TextView = view.findViewById(R.id.away_team_score)
    private val matchTime : TextView = view.find(R.id.tv_time_match)


    fun bindItem(match: SearchMatch, listener: (SearchMatch) -> Unit) {
        teamHome.text = match.strHomeTeam
        teamAway.text = match.strAwayTeam
        teamHomeScore.text = match.intHomeScore
        teamAwayScore.text = match.intAwayScore

        if(match.dateEvent == null){
            dateMatch.text = ""
        }else{
            dateMatch.text = match.dateEvent
        }

        if(match.strTime != null) {
            val FormatParse = SimpleDateFormat("HH:mm:ss")
            FormatParse.setTimeZone(TimeZone.getTimeZone("UTC"))
            val date: Date = FormatParse.parse(match.strTime.toString())
            matchTime.text = SimpleDateFormat("HH:mm").format(date)
        }else{
            matchTime.text = ""
        }


        if (match.intHomeScore == null) teamHomeScore.text = "-"
        else teamHomeScore.text = match.intHomeScore
        if (match.intAwayScore == null) teamAwayScore.text = "-"
        else teamAwayScore.text = match.intAwayScore

        itemView.setOnClickListener {
            listener(match)
        }
    }
}
