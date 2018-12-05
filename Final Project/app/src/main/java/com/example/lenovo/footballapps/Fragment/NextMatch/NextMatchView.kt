package com.example.lenovo.footballapps.Fragment.NextMatch

import com.example.lenovo.footballapps.Model.League.League
import com.example.lenovo.footballapps.Model.Match.Match

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data : List<Match>)
    fun showLeagueMatchList(data : List<League>)
}