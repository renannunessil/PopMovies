package br.com.renannunessil.popmovies.data.models

import com.google.gson.annotations.SerializedName

class PopMoviesResponse (
    @SerializedName("results")
    val moviesList: List<Movie>
)