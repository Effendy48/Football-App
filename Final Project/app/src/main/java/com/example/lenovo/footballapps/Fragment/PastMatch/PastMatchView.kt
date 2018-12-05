package com.example.lenovo.footballapps.Fragment.PastMatch

import com.example.lenovo.footballapps.Model.League.League
import com.example.lenovo.footballapps.Model.Match.Match

interface PastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPastMatchList(data : List<Match>)
    fun showLeagueList(data : List<League>)

}