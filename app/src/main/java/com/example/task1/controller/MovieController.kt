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

class MovieController {

    val moshi : Moshi = Moshi.Builder().build()

    fun searchMovies(query: Editable, serverResponseListener: ServerResponseListener) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(OkHttpClient.Builder().build())
            .build()

        val movieApi: MovieApi = retrofit.create(MovieApi::class.java)

        movieApi.listMovies(BuildConfig.MOVIE_API_KEY, query).enqueue(object : Callback<MovieResults> {
            override fun onFailure(call: Call<MovieResults>, t: Throwable) {
                Log.v("retrofit", "call failed")
            }

            override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {
            val movieResults : MovieResults = response.body()!!
                serverResponseListener.getResult(movieResults.results)
            }
        })
    }
}