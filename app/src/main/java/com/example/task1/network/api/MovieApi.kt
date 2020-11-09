package com.example.task1.network.api

import android.text.Editable
import com.example.task1.network.Model.Movie
import com.example.task1.network.Model.MovieResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("search/movie")
    fun listMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: Editable
    ): Call<MovieResults>

    @GET("movie/top_rated")
    fun listPopularMovies(
        @Query("api_key") apiKey: String
    ): Call<MovieResults>

    @GET("movie/{movie_id}")
    fun listMovieById(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String
    ): Call<Movie>

}