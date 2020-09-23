package com.example.task1.Model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    @Json(name = "release_date") val releaseDate: Int,
    val title: String,
    @Json(name = "vote_average") val voteAverage: Double
) : Parcelable