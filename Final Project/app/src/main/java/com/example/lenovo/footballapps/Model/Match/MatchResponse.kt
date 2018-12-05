package com.example.lenovo.footballapps.Model.Match

import com.google.gson.annotations.SerializedName

data class MatchResponse (
    @SerializedName("events") val matchs : List<Match>
)