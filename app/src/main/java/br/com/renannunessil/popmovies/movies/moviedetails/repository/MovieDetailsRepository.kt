package br.com.renannunessil.popmovies.movies.moviedetails.repository

import androidx.lifecycle.MutableLiveData
import br.com.renannunessil.popmovies.data.models.MovieCredits

interface MovieDetailsRepository {

    fun getMovieCredits(movieId: Int)
    fun getMovieCreditsResponseObservable(): MutableLiveData<MovieCredits>
    fun getMoviesListErrorObservable(): MutableLiveData<String>
}