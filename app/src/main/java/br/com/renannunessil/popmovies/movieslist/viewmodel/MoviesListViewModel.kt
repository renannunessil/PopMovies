package br.com.renannunessil.popmovies.movieslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.movieslist.repository.MoviesListRepository

class MoviesListViewModel(repository: MoviesListRepository) {

    private val moviesListRepository = repository
    private lateinit var moviesListResponseLiveData: MutableLiveData<List<Movie>>

    fun getMoviesListResponseObservable(): LiveData<List<Movie>> {
        if (!::moviesListResponseLiveData.isInitialized) {
            moviesListResponseLiveData = moviesListRepository.getMoviesListResponseObservable()
        }

        return moviesListResponseLiveData
    }

    fun getPopMovies() {
        moviesListRepository.getPopMovies()
    }
}