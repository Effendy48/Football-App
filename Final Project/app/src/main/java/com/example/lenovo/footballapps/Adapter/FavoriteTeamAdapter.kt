package com.example.lenovo.footballapps.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lenovo.footballapps.Model.Favorite.FavoriteTeam
import com.example.lenovo.footballapps.R
import com.squareup.picasso.Picasso

class FavoriteTeamAdapter(private var favoriteTeam: MutableList<FavoriteTeam>,
                          var listener : (FavoriteTeam) -> Unit):
                        RecyclerView.Adapter<FavoriteTeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder {
        return FavoriteTeamViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent,false))
    }

    override fun getItemCount(): Int {
      return  favoriteTeam.size
    }

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(favoriteTeam[position],listener)
    }

}

class FavoriteTeamViewHolder (view : View) : RecyclerView.ViewHolder(view) {
    private val tvTeam : TextView = view.findViewById(R.id.tv_teams)
    private val iv_Team : ImageView = view.findViewById(R.id.img_teams)

    fun bindItem(team : FavoriteTeam, listener: (FavoriteTeam) -> Unit){
        tvTeam.text = team.strTeam
        Picasso.get().load(team.strTeamBadge).into(iv_Team)

        itemView.setOnClickListener {
            listener(team)
        }
    }
}
