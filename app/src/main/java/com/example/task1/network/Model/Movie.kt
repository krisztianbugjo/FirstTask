package com.example.task1.network.Model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    @Json(name = "release_date") val releaseDate: String,
    val title: String,
    @Json(name = "vote_average") val voteAverage: Double
) : Parcelable