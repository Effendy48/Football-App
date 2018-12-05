package com.example.lenovo.footballapps.Model.Favorite

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTeam  (
        val Id : Long?,
        val IdTeam: String?,
        //val idSoccerXML: String?,
        //val intLoved: String?,
        val strTeam: String?,
        //val strTeamShort: String?,
        //val strAlternate: String?,
        //val intFormedYear: String?,
        //val strSport: String?,
       // val strLeague: String?,
        //val idLeague: String?,
        //val strDivision: String?,
        //val strManager: String?,
        val strStadium: String?,
        //val strKeywords: String?,
        //val strRSS: String?,
        //val strStadiumThumb: String?,
        //val strStadiumDescription: String?,
        //val strStadiumLocation: String?,
        //val intStadiumCapacity: String?,
        //val strWebsite: String?,
        //val strFacebook: String?,
        //val strTwitter: String?,
        //val strInstagram: String?,
        val strDescriptionEN: String?,
        //val strDescriptionDE: String?,
        //val strDescriptionFR: String?,
        //val strDescriptionCN: String?,
        //val strDescriptionIT: String?,
        //val strDescriptionJP: String?,
        //val strDescriptionRU: String?,
        //val strDescriptionES: String?,
        //val strDescriptionPT: String?,
        //val strDescriptionSE: String?,
        //val strDescriptionNL: String?,
        //val strDescriptionHU: String?,
        //val strDescriptionNO: String?,
        //val strDescriptionIL: String?,
        //val strDescriptionPL: String?,
        //val strGender: String?,
        //val strCountry: String?,
        val strTeamBadge: String?,
        //val strTeamJersey: String?,
        val strTeamLogo: String?,
        /*val strTeamFanart1: String?,
        val strTeamFanart2: String?,
        val strTeamFanart3: String?,
        val strTeamFanart4: String?,
        */val strTeamBanner: String?) : Parcelable{
        //val strYoutube: String?,
        //val strLocked: String?) : Parcelable {

    companion object {
        const val TABLE_FAVORITE_TEAM : String = "TABLE_FAVORITE_TEAM"
        const val ID : String = "ID_"
        const val ID_TEAM : String = "ID_TEAM"
        const val STR_TEAM : String = "STR_TEAM"
        //const val YEAR : String = "YEAR"
        const val STADIUM : String = "STADIUM"
        const val DESCRIPTION : String = "DESCRIPTION"
        const val TEAM_BADGE : String = "TEAM_BADGE"
        const val TEAM_LOGO : String = "TEAM_LOGO"
        //const val TEAM_JERSEY : String = "TEAM_JERSEY"
        const val TEAM_BANNER : String = "TEAM_BANNER"

    }
}