package com.example.lenovo.footballapps.Model.SearchTeam

import com.google.gson.annotations.SerializedName

data class SearchTeamResponse (
    @SerializedName("teams") var teams : List<SearchTeam>
)
