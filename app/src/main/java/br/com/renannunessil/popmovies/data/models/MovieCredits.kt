package br.com.renannunessil.popmovies.data.models

import com.google.gson.annotations.SerializedName

data class MovieCredits (

    @SerializedName("id")
    val id: Int,

    @SerializedName("crew")
    val crew: List<CrewMember>
)
