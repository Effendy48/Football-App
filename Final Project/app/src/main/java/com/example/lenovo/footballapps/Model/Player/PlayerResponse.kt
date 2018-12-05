package com.example.lenovo.footballapps.Model.Player

import com.google.gson.annotations.SerializedName

data class PlayerResponse (
        @SerializedName("player") val players : List<Player>
)