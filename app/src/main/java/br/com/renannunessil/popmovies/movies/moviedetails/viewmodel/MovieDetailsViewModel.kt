package br.com.renannunessil.popmovies.movies.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.renannunessil.popmovies.data.models.MovieCredits
import br.com.renannunessil.popmovies.movies.moviedetails.repository.MovieDetailsRepository

class MovieDetailsViewModel(repository: MovieDetailsRepository): ViewModel() {

    private val movieDetailsRepository = repository
    private lateinit var movieCreditsResponseLiveData: MutableLiveData<MovieCredits>

    fun getMovieCreditsResponseObservable(): LiveData<MovieCredits> {
        if (!::movieCreditsResponseLiveData.isInitialized) {
            movieCreditsResponseLiveData = movieDetailsRepository.getMovieCreditsResponseObservable()
        }

        return movieCreditsResponseLiveData
    }

    fun getMovieCredits(movieId: Int) {
        movieDetailsRepository.getMovieCredits(movieId)
    }
}