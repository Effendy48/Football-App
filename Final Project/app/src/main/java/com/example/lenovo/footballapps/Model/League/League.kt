package com.example.lenovo.footballapps.Model.League

import com.google.gson.annotations.SerializedName

data class League (
        @SerializedName("strLeague") var strLeague: String?,
        @SerializedName("idLeague") var idLeague: String?
){
    override fun toString() : String{
        return "League (strLeague=$strLeague, idLeague=$idLeague)"
    }
}