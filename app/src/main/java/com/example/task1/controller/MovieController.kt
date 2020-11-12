package com.example.task1.controller

import android.text.Editable
import com.example.task1.BuildConfig
import com.example.task1.network.Model.Movie
import com.example.task1.network.Model.MovieResults
import com.example.task1.network.ServerResponseListener
import com.example.task1.network.ServerResponseListenerForMovie
import com.example.task1.network.api.MovieApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val baseUrl: String = "https://api.themoviedb.org/3/"

object MovieController {

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(OkHttpClient.Builder().build())
        .build()

    val movieApi: MovieApi = retrofit.create(MovieApi::class.java)

    fun searchMovies(query: Editable, serverResponseListener: ServerResponseListener) {
        movieApi.listMovies(BuildConfig.MOVIE_API_KEY, query)
            .enqueue(object : Callback<MovieResults> {
                override fun onFailure(call: Call<MovieResults>, t: Throwable) {
                    //no-op
                }

                override fun onResponse(
                    call: Call<MovieResults>,
                    response: Response<MovieResults>
                ) {
                    val movieResults: MovieResults = response.body()!!
                    serverResponseListener.getResult(movieResults.results ?: emptyList())
                }
            })
    }

    fun searchPopularMovies(serverResponseListener: ServerResponseListener) {
        movieApi.listPopularMovies(BuildConfig.MOVIE_API_KEY)
            .enqueue(object : Callback<MovieResults> {
                override fun onFailure(call: Call<MovieResults>, t: Throwable) {
                    //no-op
                }

                override fun onResponse(
                    call: Call<MovieResults>,
                    response: Response<MovieResults>
                ) {
                    val movieResults: MovieResults = response.body()!!
                    serverResponseListener.getResult(movieResults.results ?: emptyList())
                }
            })
    }

    fun searchMoviesById(id: Int, serverResponseListener: ServerResponseListenerForMovie) {
        movieApi.listMovieById(id, BuildConfig.MOVIE_API_KEY)
            .enqueue(object : Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    //no-op
                }

                override fun onResponse(
                    call: Call<Movie>,
                    response: Response<Movie>
                ) {
                    val movie: Movie = response.body()!!
                    serverResponseListener.getResult(movie)
                }
            })
    }
}