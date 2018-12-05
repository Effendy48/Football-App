package com.example.lenovo.footballapps.Model.League

import com.google.gson.annotations.SerializedName

data class LeagueResponse (
        @SerializedName("leagues") var leagues : List<League>
)