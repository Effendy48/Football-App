package com.example.lenovo.footballapps

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.lenovo.footballapps.Fragment.FavoriteMatch.FavoriteMatch
import com.example.lenovo.footballapps.Fragment.FavoriteTeam.FavoriteTeam
import com.example.lenovo.footballapps.Fragment.NextMatch.NextMatch
import com.example.lenovo.footballapps.Fragment.PastMatch.PastMatch
import com.example.lenovo.footballapps.Fragment.SearchMatch.SearchMatch
import com.example.lenovo.footballapps.Fragment.SearchTeam.SearchTeam
import com.example.lenovo.footballapps.Fragment.Team.Team
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        if(savedInstanceState == null){
            loadNextMatch(savedInstanceState)
        }

        nav_view.setNavigationItemSelectedListener{
            item ->
            when (item.itemId) {
                R.id.nav_match_result -> {
                    loadPastMatch(savedInstanceState)
                }
                R.id.nav_match_schedule -> {
                    loadNextMatch(savedInstanceState)
                }
                R.id.nav_teams -> {
                    loadTeam(savedInstanceState)
                }
                R.id.nav_search_matchs -> {
                    startActivity<SearchMatch>()
                }
                R.id.nav_search_teams -> {
                    startActivity<SearchTeam>()
                }
                R.id.nav_favorite_teams -> {
                    loadFavoriteTeam(savedInstanceState)
                }
                R.id.nav_favorite_matchs->{
                    loadFavoriteMatch(savedInstanceState)
                }
            }

            drawer_layout.closeDrawer(GravityCompat.START)
             true
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /*override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

    }*/
    private fun loadPastMatch(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, PastMatch(), PastMatch::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadNextMatch(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextMatch(), NextMatch::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadTeam(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, Team(), Team::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadFavoriteMatch(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteMatch(), FavoriteMatch::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadFavoriteTeam(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteTeam(), FavoriteTeam::class.java.simpleName)
                    .commit()
        }
    }

}
