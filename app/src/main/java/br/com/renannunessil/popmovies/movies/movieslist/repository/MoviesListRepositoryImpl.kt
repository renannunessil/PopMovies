package br.com.renannunessil.popmovies.movies.movieslist.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.renannunessil.popmovies.ConnectionUtil
import br.com.renannunessil.popmovies.Constants
import br.com.renannunessil.popmovies.R
import br.com.renannunessil.popmovies.data.Keys
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.data.models.PopMoviesResponse
import br.com.renannunessil.popmovies.data.remote.MoviesApi
import br.com.renannunessil.popmovies.data.remote.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListRepositoryImpl(private val connectionUtil: ConnectionUtil, private val context: Context): MoviesListRepository {

    private val service: MoviesApi = RetrofitClientInstance.create()
    private lateinit var moviesListResponseLiveData: MutableLiveData<List<Movie>>
    private lateinit var moviesListErrorLiveData: MutableLiveData<String>

    override fun getPopMovies() {
        if (connectionUtil.isConnected()) {
            service.getPopMovies(Keys.API_KEY, Constants.LANGUAGE).enqueue(object :
                Callback<PopMoviesResponse> {
                override fun onFailure(call: Call<PopMoviesResponse>, throwable: Throwable) {
                    Log.d("ApiError", "Error: ${throwable.message}")
                }

                override fun onResponse(
                    call: Call<PopMoviesResponse>,
                    response: Response<PopMoviesResponse>
                ) {
                    val popMoviesResponse = response.body()
                    moviesListResponseLiveData.value = popMoviesResponse?.moviesList
                }
            })
        } else {
            moviesListErrorLiveData.value = context.getString(R.string.verify_connection)
        }
    }

    override fun getMoviesListResponseObservable(): MutableLiveData<List<Movie>> {
        if (!::moviesListResponseLiveData.isInitialized) {
            moviesListResponseLiveData = MutableLiveData()
        }

        return moviesListResponseLiveData
    }

    override fun getMoviesListErrorObservable(): MutableLiveData<String> {
        if (!::moviesListErrorLiveData.isInitialized) {
            moviesListErrorLiveData = MutableLiveData()
        }

        return moviesListErrorLiveData
    }

    object MoviesListRepositoryProvider {
        fun provideMoviesListRepository(connectionUtil: ConnectionUtil, context: Context): MoviesListRepository {
            return MoviesListRepositoryImpl(connectionUtil, context)
        }
    }
}