package br.com.renannunessil.popmovies.movies.moviedetails.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.renannunessil.popmovies.ConnectionUtil
import br.com.renannunessil.popmovies.Constants
import br.com.renannunessil.popmovies.R
import br.com.renannunessil.popmovies.data.Keys
import br.com.renannunessil.popmovies.data.models.MovieCredits
import br.com.renannunessil.popmovies.data.remote.MoviesApi
import br.com.renannunessil.popmovies.data.remote.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsRepositoryImpl(
    private val connectionUtil: ConnectionUtil,
    private val context: Context
) : MovieDetailsRepository {

    private val service: MoviesApi = RetrofitClientInstance.create()
    private lateinit var movieCreditsResponseLiveData: MutableLiveData<MovieCredits>
    private lateinit var moviesListErrorLiveData: MutableLiveData<String>

    override fun getMovieCredits(movieId: Int) {
        if (connectionUtil.isConnected()) {
            service.getMovieCredits(movieId, Keys.API_KEY).enqueue(object :
                Callback<MovieCredits> {
                override fun onFailure(call: Call<MovieCredits>, throwable: Throwable) {
                    Log.d("ApiError", "Error: ${throwable.message}")
                }

                override fun onResponse(
                    call: Call<MovieCredits>,
                    response: Response<MovieCredits>
                ) {
                    movieCreditsResponseLiveData.value = response.body()
                }
            })
        } else {
            moviesListErrorLiveData.value = context.getString(R.string.verify_connection)
        }
    }

    override fun getMovieCreditsResponseObservable(): MutableLiveData<MovieCredits> {
        if (!::movieCreditsResponseLiveData.isInitialized) {
            movieCreditsResponseLiveData = MutableLiveData()
        }

        return movieCreditsResponseLiveData
    }

    override fun getMoviesListErrorObservable(): MutableLiveData<String> {
        if (!::moviesListErrorLiveData.isInitialized) {
            moviesListErrorLiveData = MutableLiveData()
        }

        return moviesListErrorLiveData
    }

    object MovieDetailsRepositoryProvider {
        fun provideMovieDetailsRepository(connectionUtil: ConnectionUtil, context: Context): MovieDetailsRepository {
            return MovieDetailsRepositoryImpl(connectionUtil, context)
        }
    }
}