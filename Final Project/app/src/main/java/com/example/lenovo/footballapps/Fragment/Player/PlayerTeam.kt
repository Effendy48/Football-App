package com.example.lenovo.footballapps.Fragment.Player

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.lenovo.footballapps.Adapter.PlayerAdapter
import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Fragment.DetailPlayer.DetailPlayer
import com.example.lenovo.footballapps.Model.Player.Player
import com.example.lenovo.footballapps.R
import com.google.gson.Gson
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class PlayerTeam : AppCompatActivity(), PlayerTeamView {
    private val playerTeam : MutableList<Player> = mutableListOf()
    private lateinit var rv_player_team : RecyclerView
    private lateinit var presenter : PlayerTeamPresenter
    private lateinit var playerAdapter : PlayerAdapter
    private lateinit var id : String

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showPlayerList(data: List<Player>) {
        playerTeam.clear()
        playerTeam.addAll(data)
        playerAdapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_team)

        id = intent.getStringExtra("id_team")

        presenter = PlayerTeamPresenter(this, ApiRepository(), Gson())

        rv_player_team = this.find(R.id.rv_player)
        playerAdapter = PlayerAdapter(playerTeam){
            startActivity<DetailPlayer>(DetailPlayer.POSITION_EXTRA to it)
        }
        rv_player_team.adapter = playerAdapter
        rv_player_team.layoutManager = LinearLayoutManager(ctx)
        presenter.getPlayerTeam(id)
    }
}
