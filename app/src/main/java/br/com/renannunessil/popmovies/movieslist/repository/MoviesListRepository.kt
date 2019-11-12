package br.com.renannunessil.popmovies.movieslist.repository

import androidx.lifecycle.MutableLiveData
import br.com.renannunessil.popmovies.data.models.Movie

interface MoviesListRepository {

    fun getPopMovies()
    fun getMoviesListResponseObservable(): MutableLiveData<List<Movie>>
}