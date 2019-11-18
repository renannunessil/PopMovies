package br.com.renannunessil.popmovies.movies.movieslist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.renannunessil.popmovies.data.JsonUtils
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.data.models.PopMoviesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MoviesListRepositoryMock(private val context: Context): MoviesListRepository {

    private lateinit var moviesListResponseLiveData: MutableLiveData<List<Movie>>
    private val moviesListResponseFile = "movies_list_response.json"

    override fun getPopMovies() {
        val jsonResponse = JsonUtils.getAssetJsonData(context, moviesListResponseFile)
        val type = TypeToken.get(PopMoviesResponse::class.java)
        val response = Gson().fromJson<PopMoviesResponse>(jsonResponse, type.type)

        moviesListResponseLiveData.value = response.moviesList
    }

    override fun getMoviesListResponseObservable(): MutableLiveData<List<Movie>> {
        if (!::moviesListResponseLiveData.isInitialized) {
            moviesListResponseLiveData = MutableLiveData()
        }

        return moviesListResponseLiveData
    }

    override fun getMoviesListErrorObservable(): MutableLiveData<String> {
        return MutableLiveData()
    }

    object MoviesListRepositoryProvider {
        fun provideMoviesListRepository(context: Context): MoviesListRepository {
            return MoviesListRepositoryMock(context)
        }
    }
}