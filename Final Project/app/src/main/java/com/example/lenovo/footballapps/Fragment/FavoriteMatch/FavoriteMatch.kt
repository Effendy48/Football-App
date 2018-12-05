package com.example.lenovo.footballapps.Fragment.FavoriteMatch

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lenovo.footballapps.Adapter.FavoriteMatchAdapter
import com.example.lenovo.footballapps.Db.database
import com.example.lenovo.footballapps.Fragment.DetailMatch.DetailMatch
import com.example.lenovo.footballapps.Fragment.DetailMatchFavorite.DetailMatchFavorite
import com.example.lenovo.footballapps.Model.Favorite.FavoriteMatch

import com.example.lenovo.footballapps.R
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteMatch : Fragment(), FavoriteMatchView {
    override fun showFavoriteMatch(data: List<FavoriteMatch>) {
        favoriteMatch.clear()
        favoriteMatch.addAll(data)
        favoriteMatchAdapter.notifyDataSetChanged()
        swipe.isRefreshing = false
    }

    private lateinit var listMatchFavorit : RecyclerView
    private var favoriteMatch: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var favoriteMatchAdapter : FavoriteMatchAdapter
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var presenter: FavoriteMatchPresenter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteMatchAdapter = FavoriteMatchAdapter(favoriteMatch){
            ctx.startActivity<DetailMatchFavorite>(DetailMatchFavorite.POSITION_EXTRA to it)
        }

        presenter = FavoriteMatchPresenter(this, ctx)

        listMatchFavorit = this.find(R.id.rv_favorite_match)
        listMatchFavorit.layoutManager = LinearLayoutManager(ctx)
        listMatchFavorit.adapter = favoriteMatchAdapter
        swipe = this.find(R.id.swiperefresh_FavoriteMatch)
        swipe.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark)

        presenter.getFavoriteMatch()


    }

    override fun onResume(){
        super.onResume()
        presenter.getFavoriteMatch()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }
}
