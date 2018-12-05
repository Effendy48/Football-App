package com.example.lenovo.footballapps.Api

import com.example.lenovo.footballapps.BuildConfig

object TheSportDbApi {
    fun getNextMatch(id: String): String {
        return "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=${id}"
    }

    fun getTeamsLeague(id: String): String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=${id}"
    }

    fun getAllLeague(): String {
        return "https://www.thesportsdb.com/api/v1/json/1/all_leagues.php"
    }

    fun getDetailTeam(id : String) : String{
        return "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=${id}"
    }

    fun getPlayerTeam(id : String) : String{
        return "https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id=${id}"
    }

    fun getSearchTeams(name_teams: String): String {
        return "https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=${name_teams}"
    }

    fun getSearchMatch(name_match: String): String {
        return "https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=${name_match}"
    }

    fun getPastMatch(id: String): String {
        return "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=${id}"
    }
}