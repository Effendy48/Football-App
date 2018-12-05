package com.example.lenovo.footballapps

import com.example.lenovo.footballapps.Api.ApiRepository
import com.example.lenovo.footballapps.Api.TheSportDbApi
import com.example.lenovo.footballapps.Fragment.NextMatch.NextMatchPresenter
import com.example.lenovo.footballapps.Fragment.NextMatch.NextMatchView
import com.example.lenovo.footballapps.Fragment.PastMatch.PastMatchPresenter
import com.example.lenovo.footballapps.Fragment.PastMatch.PastMatchView
import com.example.lenovo.footballapps.Fragment.Player.PlayerTeamPresenter
import com.example.lenovo.footballapps.Fragment.Player.PlayerTeamView
import com.example.lenovo.footballapps.Fragment.SearchMatch.SearchMatchPresenter
import com.example.lenovo.footballapps.Fragment.SearchMatch.SearchMatchView
import com.example.lenovo.footballapps.Fragment.Team.TeamPresenter
import com.example.lenovo.footballapps.Fragment.Team.TeamView
import com.example.lenovo.footballapps.Model.Match.Match
import com.example.lenovo.footballapps.Model.Match.MatchResponse
import com.example.lenovo.footballapps.Model.Player.Player
import com.example.lenovo.footballapps.Model.Player.PlayerResponse
import com.example.lenovo.footballapps.Model.SearchMatch.SearchMatch
import com.example.lenovo.footballapps.Model.SearchMatch.SearchMatchResponse
import com.example.lenovo.footballapps.Model.Team.TeamData
import com.example.lenovo.footballapps.Model.Team.TeamResponse
import com.google.gson.Gson
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainActivityTest {
    @Mock
    private lateinit var Api: ApiRepository
    @Mock
    private lateinit var nextMatchpresenter: NextMatchPresenter
    @Mock
    private lateinit var pastMatchPresenter: PastMatchPresenter
    @Mock
    private lateinit var teamPresenter: TeamPresenter
    @Mock
    private lateinit var playerPresenter: PlayerTeamPresenter
    @Mock
    private lateinit var searchMatchPresenter: SearchMatchPresenter
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var pastMatchView: PastMatchView
    @Mock
    private lateinit var nextMatchview: NextMatchView
    @Mock
    private lateinit var teamView: TeamView
    @Mock
    private lateinit var playerTeamView: PlayerTeamView
    @Mock
    private lateinit var searchMatchView: SearchMatchView

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        nextMatchpresenter = NextMatchPresenter(nextMatchview, Api, gson, TestContextProvider())
        pastMatchPresenter = PastMatchPresenter(pastMatchView, Api, gson, TestContextProvider())
        teamPresenter = TeamPresenter(teamView, Api, gson, TestContextProvider())
        playerPresenter = PlayerTeamPresenter(playerTeamView, Api, gson, TestContextProvider())
        searchMatchPresenter = SearchMatchPresenter(searchMatchView, Api, gson, TestContextProvider())

    }

    @Test
    fun getSearchMatch() {
        val dataMatch: MutableList<SearchMatch> = mutableListOf()
        val response = SearchMatchResponse(dataMatch)
        val id = "1234"
        `when`(gson.fromJson(Api.doRequest(TheSportDbApi.getSearchMatch(id)), SearchMatchResponse::class.java))
                .thenReturn(response)
        searchMatchPresenter.getSearchMatch(id)

        verify(searchMatchView).showLoading()
        verify(searchMatchView).showSearchMatchList(response.resultSearchMatch)
    }

    @Test
    fun getPlayer() {
        val dataPlayer: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(dataPlayer)
        val id = "1234"
        `when`(gson.fromJson(Api.doRequest(TheSportDbApi.getPlayerTeam(id)), PlayerResponse::class.java))
                .thenReturn(response)
        playerPresenter.getPlayerTeam(id)
        verify(playerTeamView).showLoading()
        verify(playerTeamView).showPlayerList(response.players)
    }

    @Test
    fun getTeamLeague() {
        val dataTeam: MutableList<TeamData> = mutableListOf()
        val response = TeamResponse(dataTeam)
        val id = "1234"
        `when`(gson.fromJson(Api.doRequest(TheSportDbApi.getTeamsLeague(id)), TeamResponse::class.java))
                .thenReturn(response)
        teamPresenter.getTeamsLeague(id)

        verify(teamView).showLoading()
        verify(teamView).showTeamList(response.teams)
    }

    @Test
    fun getNextMatch() {
        val dataMatch: MutableList<Match> = mutableListOf()
        val response = MatchResponse(dataMatch)
        val id = "1234"
        Mockito.`when`(gson.fromJson(Api.doRequest(TheSportDbApi.getNextMatch(id)), MatchResponse::class.java))
                .thenReturn(response)
        nextMatchpresenter.getNextMatch(id)

        Mockito.verify(nextMatchview).showLoading()
        Mockito.verify(nextMatchview).showNextMatchList(response.matchs)
    }

    @Test
    fun getPastMatch() {
        val dataMatch: MutableList<Match> = mutableListOf()
        val response = MatchResponse(dataMatch)
        val id = "1234"
        Mockito.`when`(gson.fromJson(Api.doRequest(TheSportDbApi.getPastMatch(id)), MatchResponse::class.java))
                .thenReturn(response)
        pastMatchPresenter.getPastMatch(id)

        Mockito.verify(pastMatchView).showPastMatchList(response.matchs)
    }
}