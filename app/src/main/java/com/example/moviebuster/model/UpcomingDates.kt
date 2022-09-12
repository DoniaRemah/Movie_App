package com.example.moviebuster.model


import com.google.gson.annotations.SerializedName

data class UpcomingDates(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)