package com.example.lenovo.footballapps.Fragment.Team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.lenovo.footballapps.Adapter.TeamAdapter
import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Fragment.DetailTeam.DetailTeam
import com.example.lenovo.footballapps.Model.League.League
import com.example.lenovo.footballapps.Model.Team.TeamData

import com.example.lenovo.footballapps.R
import com.example.lenovo.footballapps.Utils.invisible
import com.example.lenovo.footballapps.Utils.visible
import com.google.gson.Gson
import com.jaredrummler.materialspinner.MaterialSpinner
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.startActivity

class Team : Fragment(), TeamView {
    private val teamsLeague : MutableList<TeamData> = mutableListOf()

    private lateinit var idLeague : ArrayList<String>
    private lateinit var dataLeague : ArrayList<String>

    private lateinit var rv_list_team : RecyclerView
    private lateinit var presenter: TeamPresenter
    private lateinit var progressBar : ProgressBar
    private lateinit var spinner : MaterialSpinner
    private lateinit var teamAdapter : TeamAdapter

    private var id = "4328"

    override fun showLeagueList(data: List<League>) {
        for(items in data.iterator()){
            dataLeague.add(items.strLeague.toString())
            idLeague.add(items.idLeague.toString())
            spinner.setItems(dataLeague)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<TeamData>) {
        teamsLeague.clear()
        teamsLeague.addAll(data)
        teamAdapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)

        presenter = TeamPresenter(this@Team, ApiRepository(), Gson())

        rv_list_team = this.find(R.id.list_teams)
        spinner = this.find(R.id.spinner_league_teams)
        progressBar = this.find(R.id.progress_bar)

        teamAdapter = TeamAdapter(teamsLeague){
            startActivity<DetailTeam>(DetailTeam.POSITION_EXTRA to it)
        }
        dataLeague = ArrayList()
        idLeague = ArrayList()

        rv_list_team.adapter = teamAdapter
        rv_list_team.layoutManager = LinearLayoutManager(context)

        presenter.getTeamsLeague(id)
        presenter.getAllLeagues()


        selectSpinnerLeague()

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }
    private fun selectSpinnerLeague(){
        spinner.setOnItemSelectedListener{_, position, _, _ ->
            presenter.getTeamsLeague(idLeague[position])
        }
    }
}
