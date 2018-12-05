package com.example.lenovo.footballapps.Fragment.SearchTeam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.example.lenovo.footballapps.Adapter.SearchTeamAdapter
import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Fragment.DetailSearchTeam.DetailSearchTeam
import com.example.lenovo.footballapps.Fragment.DetailTeam.DetailTeam
import com.example.lenovo.footballapps.Model.SearchTeam.SearchTeam
import com.example.lenovo.footballapps.R
import com.example.lenovo.footballapps.Utils.invisible
import com.example.lenovo.footballapps.Utils.visible
import com.google.gson.Gson
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class SearchTeam : AppCompatActivity(),SearchTeamView {
    override fun showLoading() {
       // progressBar.visible()
    }

    override fun hideLoading() {
       // progressBar.invisible()
    }

    override fun showSearchTeamList(data: List<SearchTeam>) {
        teamsList.clear()
        teamsList.addAll(data)
        searchTeamAdapter.notifyDataSetChanged()
    }

    private lateinit var presenter: SearchTeamPresenter
    private lateinit var Search : MaterialSearchView
    private lateinit var rvTeam : RecyclerView
    //private lateinit var progressBar: ProgressBar
    private lateinit var searchTeamAdapter : SearchTeamAdapter
    private val teamsList : MutableList<SearchTeam> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Search = this.find(R.id.search_team)
        rvTeam = this.find(R.id.rv_search_team)
        //progressBar = this.find(R.id.progress_bar)

        searchTeamAdapter = SearchTeamAdapter(teamsList){
            startActivity<DetailSearchTeam>(DetailSearchTeam.POSITION_EXTRA to it)
            }

        rvTeam.adapter = searchTeamAdapter
        rvTeam.layoutManager = LinearLayoutManager(this)


        presenter = SearchTeamPresenter(this, ApiRepository(), Gson())

        Search.setOnQueryTextListener(object  : MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getSearchTeam(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu : Menu) : Boolean{
        menuInflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)
        Search.setMenuItem(item)
        return true
    }
    override fun onOptionsItemSelected(item : MenuItem): Boolean{
        val id = item.itemId
        return id == R.id.action_search || super.onOptionsItemSelected(item)
    }
    override fun onBackPressed(){
        if(Search.isSearchOpen){
            Search.closeSearch()
        }else{
            super.onBackPressed()
        }
    }
}
