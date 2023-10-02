package com.fathirdev.basketball_n.data.network

import com.fathirdev.basketball_n.data.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiService {
    @GET("everything")
    fun getBasketballNews(
        @Query("q") keyWord : String = "basketball",
        @Query("language") language : String = "en",
        @Query("sortBy") sortBy : String = "popularity",
        @Query("pageSize") pageSize : Int = 30
    ) : Call<NewsResponse>

    @GET("everything")
    fun getPlayersNews(
        @Query("q") keyWord : String = "basketball player",
        @Query("language") language : String = "en",
    ) : Call<NewsResponse>


    @GET("everything")
    fun getNbaNews(
        @Query("q") keyword: String = "nba"
    ) : Call<NewsResponse>

    @GET("top-headlines")
    fun searchNews(
        @Query("q") query: String,
        @Query("category") category: String = "sports",
        @Query("pageSize") pageSize: Int = 50
    ) : Call<NewsResponse>
}