package com.example.lenovo.footballapps.Fragment.SearchMatch

import com.example.lenovo.footballapps.Model.SearchMatch.SearchMatch
import com.example.lenovo.footballapps.Model.SearchTeam.SearchTeam

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchMatchList(data : List<SearchMatch>)
}