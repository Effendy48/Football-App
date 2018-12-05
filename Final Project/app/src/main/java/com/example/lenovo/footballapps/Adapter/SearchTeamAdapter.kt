package com.example.lenovo.footballapps.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lenovo.footballapps.Model.SearchTeam.SearchTeam
import com.example.lenovo.footballapps.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class SearchTeamAdapter(private var teamsearch : MutableList<SearchTeam>,
                        private var listener : (SearchTeam) -> Unit) :
                        RecyclerView.Adapter<SearchTeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTeamViewHolder {
        return SearchTeamViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent,false))
    }

    override fun getItemCount(): Int {
       return teamsearch.size
    }

    override fun onBindViewHolder(holder: SearchTeamViewHolder, position: Int) {
        holder.bindItem(teamsearch[position],listener)
    }
}

class SearchTeamViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val tvTeam : TextView = view.findViewById(R.id.tv_teams)
    private val iv_Team : ImageView = view.findViewById(R.id.img_teams)

    fun bindItem(teamSearch : SearchTeam, listener: (SearchTeam) -> Unit){
        tvTeam.text = teamSearch.strTeam
        Picasso.get().load(teamSearch.strTeamBadge).into(iv_Team)

        itemView.setOnClickListener {
            listener(teamSearch)
        }
    }
}
