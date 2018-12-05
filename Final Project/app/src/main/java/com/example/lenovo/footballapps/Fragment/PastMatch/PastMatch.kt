package com.example.lenovo.footballapps.Fragment.PastMatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.lenovo.footballapps.Adapter.MatchAdapter
import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Fragment.DetailMatch.DetailMatch
import com.example.lenovo.footballapps.Fragment.NextMatch.NextMatchView
import com.example.lenovo.footballapps.Model.League.League
import com.example.lenovo.footballapps.Model.Match.Match

import com.example.lenovo.footballapps.R
import com.example.lenovo.footballapps.Utils.invisible
import com.example.lenovo.footballapps.Utils.visible
import com.google.gson.Gson
import com.jaredrummler.materialspinner.MaterialSpinner
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.startActivity

class PastMatch : Fragment(), PastMatchView {


    private val pastMatch : MutableList<Match> = mutableListOf()

    private lateinit var idLeague: ArrayList<String>
    private lateinit var dataLeague: ArrayList<String>

    private lateinit var rv_past_match : RecyclerView
    private lateinit var presenter: PastMatchPresenter
    private lateinit var MAdapter : MatchAdapter
    private lateinit var progressBar : ProgressBar
    private lateinit var spinner: MaterialSpinner

    private var id = "4328"

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }
    override fun showPastMatchList(data: List<Match>) {
        pastMatch.clear()
        pastMatch.addAll(data)
        MAdapter.notifyDataSetChanged()
    }

    override fun showLeagueList(data: List<League>) {
        for(items in data.iterator()){
            dataLeague.add(items.strLeague.toString())
            idLeague.add(items.idLeague.toString())

            spinner.setItems(dataLeague)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = PastMatchPresenter(this, ApiRepository(), Gson())

        rv_past_match = this.find(R.id.list_past_match)
        spinner = this.find(R.id.spinner_league_past_match)
        progressBar = this.find(R.id.progress_bar)

        MAdapter = MatchAdapter(pastMatch){
            startActivity<DetailMatch>(DetailMatch.POSITION_EXTRA to it)
        }
        dataLeague = ArrayList()
        idLeague = ArrayList()

        rv_past_match.adapter = MAdapter
        rv_past_match.layoutManager = LinearLayoutManager(context)
        presenter.getPastMatch(id)
        presenter.getAllLeagues()

        selectSpinnerLeague()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_match, container, false)
    }
    private fun selectSpinnerLeague(){
        spinner.setOnItemSelectedListener { _, position, _, _ ->
        presenter.getPastMatch(idLeague[position])
        }
    }
}
