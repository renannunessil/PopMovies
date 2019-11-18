package br.com.renannunessil.popmovies.movies.movieslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.movies.movieslist.repository.MoviesListRepository

class MoviesListViewModel(repository: MoviesListRepository) : ViewModel() {

    private val moviesListRepository = repository

    fun getMoviesListResponseObservable(): LiveData<List<Movie>> {
        return moviesListRepository.getMoviesListResponseObservable()
    }

    fun getErrorObservable(): LiveData<String> {
        return  moviesListRepository.getMoviesListErrorObservable()
    }

    fun getPopMovies() {
        moviesListRepository.getPopMovies()
    }
}