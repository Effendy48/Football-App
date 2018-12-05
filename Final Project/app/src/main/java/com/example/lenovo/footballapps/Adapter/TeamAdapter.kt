package com.example.lenovo.footballapps.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lenovo.footballapps.Model.Team.TeamData
import com.example.lenovo.footballapps.R
import com.squareup.picasso.Picasso

class TeamAdapter (private var team : MutableList<TeamData>, var listener : (TeamData)
-> Unit) : RecyclerView.Adapter<TeamViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team,parent,false))
    }

    override fun getItemCount(): Int {
        return team.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(team[position], listener)
    }

}

class TeamViewHolder (view : View) : RecyclerView.ViewHolder(view) {
    private val tvTeam : TextView = view.findViewById(R.id.tv_teams)
    private val iv_Team : ImageView = view.findViewById(R.id.img_teams)

    fun bindItem(team : TeamData, listener: (TeamData) -> Unit){
        tvTeam.text = team.strTeam
        Picasso.get().load(team.strTeamBadge).into(iv_Team)

        itemView.setOnClickListener {
            listener(team)
        }
    }
}
