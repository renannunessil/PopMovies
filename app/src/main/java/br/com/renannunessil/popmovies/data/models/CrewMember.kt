package br.com.renannunessil.popmovies.data.models

import com.google.gson.annotations.SerializedName

data class CrewMember (

    @SerializedName("name")
    val name: String,

    @SerializedName("job")
    val job: String
)