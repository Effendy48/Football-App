package com.example.lenovo.footballapps.Model.SearchMatch

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse (
 @SerializedName("event") var resultSearchMatch : List<SearchMatch>
)