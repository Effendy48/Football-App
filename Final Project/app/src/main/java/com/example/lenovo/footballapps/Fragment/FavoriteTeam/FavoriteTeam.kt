package com.example.lenovo.footballapps.Fragment.FavoriteTeam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lenovo.footballapps.Adapter.FavoriteMatchAdapter
import com.example.lenovo.footballapps.Adapter.FavoriteTeamAdapter
import com.example.lenovo.footballapps.Db.database
import com.example.lenovo.footballapps.Fragment.DetailTeam.DetailTeam
import com.example.lenovo.footballapps.Fragment.DetailTeamFavorite.DetailTeamFavorite
import com.example.lenovo.footballapps.Model.Favorite.FavoriteMatch
import com.example.lenovo.footballapps.Model.Favorite.FavoriteTeam

import com.example.lenovo.footballapps.R
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoriteTeam : Fragment(), FavoriteTeamView {
    override fun showFavoriteTeamList(data: List<FavoriteTeam>) {
        favoriteTeam.clear()
        favoriteTeam.addAll(data)
        favoriteTeamAdapter.notifyDataSetChanged()
        swipe.isRefreshing = false
    }

    private lateinit var listTeamFavorit : RecyclerView
    private var favoriteTeam : MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var favoriteTeamAdapter : FavoriteTeamAdapter
    private lateinit var swipe : SwipeRefreshLayout
    private lateinit var presenter: FavoriteTeamPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        presenter = FavoriteTeamPresenter(this, ctx)
        favoriteTeamAdapter = FavoriteTeamAdapter(favoriteTeam){
            startActivity<DetailTeamFavorite>(DetailTeamFavorite.POSITION_EXTRA to it)
        }
        swipe = this.find(R.id.swiperefresh_FavoriteTeam)
        listTeamFavorit= this.find(R.id.rv_favorite_team)

        listTeamFavorit.layoutManager = LinearLayoutManager(ctx)
        listTeamFavorit.adapter = favoriteTeamAdapter

        presenter.getFavoriteTeam()


       /* swipe.onRefresh {
            favoriteTeam.clear()
            presenter.getFavoriteTeam()
        }*/
    }


    override fun onResume() {
        super.onResume()
        presenter.getFavoriteTeam()
    }
}
