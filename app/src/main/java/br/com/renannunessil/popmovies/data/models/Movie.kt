package br.com.renannunessil.popmovies.data.models

import com.google.gson.annotations.SerializedName

data class Movie (

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPath : String,

    @SerializedName("vote_average")
    val movieEvaluation: Float,

    @SerializedName("release_date")
    val releaseDate: String,

    val movieCredits: MovieCredits
)