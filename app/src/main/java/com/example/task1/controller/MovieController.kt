package com.example.task1.controller

import android.text.Editable
import android.util.Log
import com.example.task1.BuildConfig
import com.example.task1.network.Model.MovieResults
import com.example.task1.network.ServerResponseListener
import com.example.task1.network.api.MovieApi
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val baseUrl: String = "https://api.themoviedb.org/3/"

object MovieController {

    val moshi: Moshi = Moshi.Builder().build()

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
                    Log.v("retrofit", "call failed")
                }

                override fun onResponse(
                    call: Call<MovieResults>,
                    response: Response<MovieResults>
                ) {
                    val movieResults: MovieResults = response.body()!!
                    serverResponseListener.getResult(movieResults.results)
                }
            })
    }
}