package br.com.renannunessil.popmovies.data.models

import br.com.renannunessil.popmovies.data.Paths
import com.google.gson.annotations.SerializedName

data class Movie (

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPath : String,

    @SerializedName("vote_average")
    val movieEvaluation: Float,

    @SerializedName("release_date")
    val releaseDate: String,

    var movieCredits: MovieCredits
) {
    fun getPosterCompletePath(): String {
        val stringBuilder = StringBuilder()

        stringBuilder.append(Paths.POSTERS_PATH)
        stringBuilder.append(this.posterPath)
        return stringBuilder.toString()
    }
}