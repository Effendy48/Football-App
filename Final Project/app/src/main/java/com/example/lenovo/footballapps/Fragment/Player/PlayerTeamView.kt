package com.example.lenovo.footballapps.Fragment.Player

import com.example.lenovo.footballapps.Model.Player.Player

interface PlayerTeamView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data : List<Player>)
}