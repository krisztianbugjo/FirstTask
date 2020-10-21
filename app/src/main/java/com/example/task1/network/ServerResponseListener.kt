package com.example.task1.network

import com.example.task1.network.Model.Movie

interface ServerResponseListener {
    fun getResult(results: List<Movie>)
}