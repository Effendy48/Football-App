package com.example.lenovo.footballapps.Model.Team

import com.google.gson.annotations.SerializedName

data class TeamResponse(
        @SerializedName("teams") val teams : List<TeamData>
)