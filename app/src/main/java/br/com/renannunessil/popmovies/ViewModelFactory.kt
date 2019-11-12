package br.com.renannunessil.popmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.renannunessil.popmovies.movies.movieslist.repository.MoviesListRepositoryImpl
import br.com.renannunessil.popmovies.movies.movieslist.viewmodel.MoviesListViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
            val repository =
                MoviesListRepositoryImpl.MoviesListRepositoryProvider.provideMoviesListRepository()
            MoviesListViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}