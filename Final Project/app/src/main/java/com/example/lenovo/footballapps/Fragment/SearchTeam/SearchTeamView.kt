package com.example.lenovo.footballapps.Fragment.SearchTeam

import com.example.lenovo.footballapps.Model.SearchTeam.SearchTeam

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showSearchTeamList(data : List<SearchTeam>)
}