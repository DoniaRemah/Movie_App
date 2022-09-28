package com.example.moviebuster.model


import com.google.gson.annotations.SerializedName

data class UpcomingModel(
    @SerializedName("dates")
    val dates: UpcomingDates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val UpcomingList: List<UpcomingList>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

