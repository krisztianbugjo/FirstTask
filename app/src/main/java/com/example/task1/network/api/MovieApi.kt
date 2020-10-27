package com.example.task1.network.api

import android.text.Editable
import com.example.task1.network.Model.MovieResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("search/movie")
    fun listMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: Editable
    ): Call<MovieResults>
}