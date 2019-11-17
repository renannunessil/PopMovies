package br.com.renannunessil.popmovies

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.renannunessil.popmovies.movies.moviedetails.repository.MovieDetailsRepository
import br.com.renannunessil.popmovies.movies.moviedetails.repository.MovieDetailsRepositoryImpl
import br.com.renannunessil.popmovies.movies.moviedetails.viewmodel.MovieDetailsViewModel
import br.com.renannunessil.popmovies.movies.movieslist.repository.MoviesListRepositoryImpl
import br.com.renannunessil.popmovies.movies.movieslist.viewmodel.MoviesListViewModel
import br.com.renannunessil.popmovies.movies.sharedviewmodel.SelectedMovieViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesListViewModel::class.java) -> {
                val repository =
                    MoviesListRepositoryImpl.MoviesListRepositoryProvider.provideMoviesListRepository()
                MoviesListViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MovieDetailsViewModel::class.java) -> {
                val repository =
                    MovieDetailsRepositoryImpl.MovieDetailsRepositoryProvider.provideMovieDetailsRepository()
                MovieDetailsViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SelectedMovieViewModel::class.java) -> SelectedMovieViewModel(context) as T
            else -> throw IllegalArgumentException("ViewModel not found")
        }
    }
}