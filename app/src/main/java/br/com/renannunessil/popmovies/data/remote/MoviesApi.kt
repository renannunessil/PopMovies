package br.com.renannunessil.popmovies.data.remote

import androidx.lifecycle.LiveData
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.data.models.MovieCredits
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET
    fun getPopMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<List<Movie>>

    fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieCredits>
}