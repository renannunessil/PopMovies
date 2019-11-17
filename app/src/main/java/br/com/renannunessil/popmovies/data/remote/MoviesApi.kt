package br.com.renannunessil.popmovies.data.remote

import androidx.lifecycle.LiveData
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.data.models.MovieCredits
import br.com.renannunessil.popmovies.data.models.PopMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("popular")
    fun getPopMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<PopMoviesResponse>

    @GET("{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieCredits>
}