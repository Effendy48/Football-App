package com.example.lenovo.footballapps.Model.Player

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player (
        @SerializedName("idPlayer") var idPlayer : String?,
        @SerializedName("idTeam") var idTeam : String?,
        @SerializedName("strPlayer") var strPlayer: String?,
        @SerializedName("strTeam") var strTeam : String?,
        @SerializedName("strNationality") var strNationality : String?,
        @SerializedName("dateBorn") var dateBorn : String?,
        @SerializedName("strBirthLocation") var strBirthLocation : String?,
        @SerializedName("strDescriptionEN") var strDescriptionEN : String?,
        @SerializedName("strPosition") val strPosition : String?,
        @SerializedName("strThumb") val strThumb : String?,
        @SerializedName("strCutout") val strCutOut : String?
        ):Parcelable