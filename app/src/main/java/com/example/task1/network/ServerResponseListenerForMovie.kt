package com.example.task1.network

import com.example.task1.network.Model.Movie

interface ServerResponseListenerForMovie {
    fun getResult(result: Movie)
}