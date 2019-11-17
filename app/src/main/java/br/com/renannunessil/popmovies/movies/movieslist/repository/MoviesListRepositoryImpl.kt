package br.com.renannunessil.popmovies.movies.movieslist.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.renannunessil.popmovies.Constants
import br.com.renannunessil.popmovies.data.Keys
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.data.models.PopMoviesResponse
import br.com.renannunessil.popmovies.data.remote.MoviesApi
import br.com.renannunessil.popmovies.data.remote.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListRepositoryImpl: MoviesListRepository {

    private val service: MoviesApi = RetrofitClientInstance.create()
    private lateinit var moviesListResponseLiveData: MutableLiveData<List<Movie>>

    override fun getPopMovies() {
        service.getPopMovies(Keys.API_KEY, Constants.LANGUAGE).enqueue(object :
            Callback<PopMoviesResponse> {
            override fun onFailure(call: Call<PopMoviesResponse>, throwable: Throwable) {
                Log.d("ApiError", "Error: ${throwable.message}")
            }

            override fun onResponse(call: Call<PopMoviesResponse>, response: Response<PopMoviesResponse>) {
                    val popMoviesResponse = response.body()
                    moviesListResponseLiveData.value = popMoviesResponse?.moviesList
            }
        })
    }

    override fun getMoviesListResponseObservable(): MutableLiveData<List<Movie>> {
        if (!::moviesListResponseLiveData.isInitialized) {
            moviesListResponseLiveData = MutableLiveData()
        }

        return moviesListResponseLiveData
    }

    object MoviesListRepositoryProvider {
        fun provideMoviesListRepository(): MoviesListRepository {
            return MoviesListRepositoryImpl()
        }
    }
}