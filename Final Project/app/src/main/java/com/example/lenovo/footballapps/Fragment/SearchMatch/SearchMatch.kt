package com.example.lenovo.footballapps.Fragment.SearchMatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.lenovo.footballapps.Adapter.SearchMatchAdapter
import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Fragment.DetailSearchMatch.DetailSearchMatch
import com.example.lenovo.footballapps.Model.SearchMatch.SearchMatch
import com.example.lenovo.footballapps.R
import com.google.gson.Gson
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.miguelcatalan.materialsearchview.SearchAdapter
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class SearchMatch : AppCompatActivity(),SearchMatchView {
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showSearchMatchList(data: List<SearchMatch>) {
        matchList.clear()
        matchList.addAll(data)
        searchMatchAdapter.notifyDataSetChanged()

    }

    private val matchList : MutableList<SearchMatch> = mutableListOf()
    private lateinit var searchMatchPresenter: SearchMatchPresenter
    private lateinit var Search : MaterialSearchView
    private lateinit var rvMatch : RecyclerView
    private lateinit var searchMatchAdapter: SearchMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Search = this.find(R.id.search_match)
        rvMatch = this.find(R.id.rv_search_match)


        searchMatchAdapter = SearchMatchAdapter(matchList){
            startActivity<DetailSearchMatch>(DetailSearchMatch.POSITION_EXTRA to it)

        }


        rvMatch.adapter = searchMatchAdapter
        rvMatch.layoutManager = LinearLayoutManager(this)

        searchMatchPresenter = SearchMatchPresenter(this, ApiRepository(), Gson())

        Search.setOnQueryTextListener(object  : MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                searchMatchPresenter.getSearchMatch(query)
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
