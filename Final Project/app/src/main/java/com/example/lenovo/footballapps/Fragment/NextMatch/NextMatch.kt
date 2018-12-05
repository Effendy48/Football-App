package com.example.lenovo.footballapps.Fragment.NextMatch


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.lenovo.footballapps.Adapter.MatchAdapter
import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Fragment.DetailMatch.DetailMatch
import com.example.lenovo.footballapps.Model.League.League
import com.example.lenovo.footballapps.Model.Match.Match

import com.example.lenovo.footballapps.R
import com.example.lenovo.footballapps.Utils.invisible
import com.example.lenovo.footballapps.Utils.visible
import com.google.gson.Gson
import com.jaredrummler.materialspinner.MaterialSpinner
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class NextMatch : Fragment(), NextMatchView {
    private val nextMatch : MutableList<Match> = mutableListOf()

    private lateinit var idLeague : ArrayList<String>
    private lateinit var dataLeague : ArrayList<String>

    private lateinit var rv_next_match : RecyclerView
    private lateinit var presenter : NextMatchPresenter
    private lateinit var MAdapter : MatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: MaterialSpinner

    private var id = "4328"

    override fun showLeagueMatchList(data: List<League>) {
        for (items in data.iterator()){
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

    override fun showNextMatchList(data: List<Match>) {
        nextMatch.clear()
        nextMatch.addAll(data)
        MAdapter.notifyDataSetChanged()
          }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        presenter = NextMatchPresenter(this, ApiRepository(), Gson())

        rv_next_match = this.find(R.id.list_match)
        spinner = this.find(R.id.spinner_league_next_match)
        progressBar = this.find(R.id.progress_bar)

        MAdapter = MatchAdapter(nextMatch){
            startActivity<DetailMatch>(DetailMatch.POSITION_EXTRA to it)
        }

        dataLeague = ArrayList()
        idLeague = ArrayList()


        rv_next_match.adapter = MAdapter
        rv_next_match.layoutManager = LinearLayoutManager(context)
        presenter.getNextMatch(id)
        presenter.getAllLeagues()

        selectSpinnerLeague()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View{
        return inflater.inflate(R.layout.fragment_next_match, container, false)
        }
    private fun selectSpinnerLeague()  {
        spinner.setOnItemSelectedListener{_, position, _, _ ->
            presenter.getNextMatch(idLeague[position])
            }
        }
    }

