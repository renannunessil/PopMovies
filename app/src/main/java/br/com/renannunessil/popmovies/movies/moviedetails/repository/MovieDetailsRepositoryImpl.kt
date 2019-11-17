package br.com.renannunessil.popmovies.movies.moviedetails.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.renannunessil.popmovies.Constants
import br.com.renannunessil.popmovies.data.Keys
import br.com.renannunessil.popmovies.data.models.MovieCredits
import br.com.renannunessil.popmovies.data.remote.MoviesApi
import br.com.renannunessil.popmovies.data.remote.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsRepositoryImpl : MovieDetailsRepository {

    private val service: MoviesApi = RetrofitClientInstance.create()
    private lateinit var movieCreditsResponseLiveData: MutableLiveData<MovieCredits>

    override fun getMovieCredits(movieId: Int) {

        service.getMovieCredits(movieId, Keys.API_KEY).enqueue(object :
            Callback<MovieCredits> {
            override fun onFailure(call: Call<MovieCredits>, throwable: Throwable) {
                Log.d("ApiError", "Error: ${throwable.message}")
            }

            override fun onResponse(call: Call<MovieCredits>, response: Response<MovieCredits>) {
                movieCreditsResponseLiveData.value = response.body()
            }
        })
    }

    override fun getMovieCreditsResponseObservable(): MutableLiveData<MovieCredits> {
        if (!::movieCreditsResponseLiveData.isInitialized) {
            movieCreditsResponseLiveData = MutableLiveData()
        }

        return movieCreditsResponseLiveData
    }

    object MovieDetailsRepositoryProvider {
        fun provideMovieDetailsRepository(): MovieDetailsRepository {
            return MovieDetailsRepositoryImpl()
        }
    }
}