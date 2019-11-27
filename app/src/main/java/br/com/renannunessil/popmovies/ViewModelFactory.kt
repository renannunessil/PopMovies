package br.com.renannunessil.popmovies

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.renannunessil.popmovies.movies.moviedetails.repository.MovieDetailsRepositoryImpl
import br.com.renannunessil.popmovies.movies.moviedetails.repository.MovieDetailsRepositoryMock
import br.com.renannunessil.popmovies.movies.moviedetails.viewmodel.MovieDetailsViewModel
import br.com.renannunessil.popmovies.movies.movieslist.repository.MoviesListRepositoryImpl
import br.com.renannunessil.popmovies.movies.movieslist.repository.MoviesListRepositoryMock
import br.com.renannunessil.popmovies.movies.movieslist.viewmodel.MoviesListViewModel
import br.com.renannunessil.popmovies.movies.sharedviewmodel.SelectedMovieViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val context: Context, private val isMock: Boolean) :
    ViewModelProvider.Factory {

    private val connectionUtil = ConnectionUtil(context)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesListViewModel::class.java) -> {
                if (!isMock) {
                    MoviesListViewModel(
                        MoviesListRepositoryImpl.MoviesListRepositoryProvider.provideMoviesListRepository(
                            connectionUtil,
                            context
                        )
                    ) as T
                } else {
                    MoviesListViewModel(
                        MoviesListRepositoryMock.MoviesListRepositoryProvider.provideMoviesListRepository(
                            context
                        )
                    ) as T
                }
            }
            modelClass.isAssignableFrom(MovieDetailsViewModel::class.java) -> {
                if (!isMock) {
                    MovieDetailsViewModel(
                        MovieDetailsRepositoryImpl.MovieDetailsRepositoryProvider.provideMovieDetailsRepository(
                            connectionUtil,
                            context
                        )
                    ) as T
                } else {
                    MovieDetailsViewModel(
                        MovieDetailsRepositoryMock.MovieDetailsRepositoryProvider.provideMovieDetailsRepository(
                            context
                        )
                    ) as T
                }
            }
            modelClass.isAssignableFrom(SelectedMovieViewModel::class.java) -> SelectedMovieViewModel(
                context
            ) as T
            else -> throw IllegalArgumentException("ViewModel not found")
        }
    }
}