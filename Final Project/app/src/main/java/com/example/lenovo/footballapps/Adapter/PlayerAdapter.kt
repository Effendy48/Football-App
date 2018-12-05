package com.example.lenovo.footballapps.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lenovo.footballapps.Model.Player.Player
import com.example.lenovo.footballapps.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class PlayerAdapter(private var players : MutableList<Player>, var listener : (Player) ->
Unit) : RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.item_player, parent, false))
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }
}

class PlayerViewHolder(view : View): RecyclerView.ViewHolder(view) {
    private val imageThumbnail : ImageView = view.findViewById(R.id.iv_player_thumb_team)
    private val playerName : TextView = view.findViewById(R.id.tv_player_name_team)
    private val playerPosition : TextView = view.findViewById(R.id.tv_player_position_team)

    fun bindItem(player : Player, listener: (Player) -> Unit){
        Picasso.get().load(player.strThumb).into(imageThumbnail)
        playerName.text = player.strPlayer
        playerPosition.text = player.strPosition

        itemView.setOnClickListener {
            listener(player)
        }
    }
}
