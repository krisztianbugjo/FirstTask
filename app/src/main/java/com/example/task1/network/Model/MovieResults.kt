package com.example.task1.network.Model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResults(
    val results: List<Movie>,
    @Json(name = "total_results") val totalResults: Int
) : Parcelable