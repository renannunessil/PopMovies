package br.com.renannunessil.popmovies.movies.moviedetails.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import br.com.renannunessil.popmovies.ConnectionUtil
import br.com.renannunessil.popmovies.data.JsonUtils
import br.com.renannunessil.popmovies.data.models.MovieCredits
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieDetailsRepositoryMock(val context: Context): MovieDetailsRepository {

    private lateinit var movieCreditsResponseLiveData: MutableLiveData<MovieCredits>
    private val movieDetailsResponseFile = "movie_details_response.json"

    override fun getMovieCredits(movieId: Int) {
        val jsonResponse = JsonUtils.getAssetJsonData(context, movieDetailsResponseFile)
        val type = TypeToken.get(MovieCredits::class.java)
        val response = Gson().fromJson<MovieCredits>(jsonResponse, type.type)

        movieCreditsResponseLiveData.value = response
    }

    override fun getMovieCreditsResponseObservable(): MutableLiveData<MovieCredits> {
        if (!::movieCreditsResponseLiveData.isInitialized) {
            movieCreditsResponseLiveData = MutableLiveData()
        }

        return movieCreditsResponseLiveData
    }

    override fun getMovieCreditsErrorObservable(): MutableLiveData<String> {
        return MutableLiveData()
    }

    object MovieDetailsRepositoryProvider {
        fun provideMovieDetailsRepository(context: Context): MovieDetailsRepository {
            return MovieDetailsRepositoryMock(context)
        }
    }
}