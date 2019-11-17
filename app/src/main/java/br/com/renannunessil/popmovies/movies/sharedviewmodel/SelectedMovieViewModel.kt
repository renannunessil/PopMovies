package br.com.renannunessil.popmovies.movies.sharedviewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.renannunessil.popmovies.R
import br.com.renannunessil.popmovies.data.models.Movie
import kotlin.text.StringBuilder

class SelectedMovieViewModel(val context : Context) : ViewModel() {

    private val selectedMovieLiveData = MutableLiveData<Movie>()
    private lateinit var selectedMovie: Movie

    fun select(movie: Movie) {
        selectedMovie = movie
        selectedMovieLiveData.value = movie
    }

    fun getSelectedMovieObservable(): LiveData<Movie> {
        return selectedMovieLiveData
    }

    fun getCrewMember(crewMemberJob: String): String {

        val crewMemberList = ArrayList<String>()

        selectedMovie.movieCredits.crew.forEach {
            if (it.job.equals(crewMemberJob)) {
                crewMemberList.add(it.name)
            }
        }

        return if (crewMemberList.size > 1) {
            buildMoreThanOneCrewMembersOnSameJobLabel(crewMemberList)
        } else if (crewMemberList.size == 1) {
            crewMemberList[0]
        } else {
            context.getString(R.string.not_found)
        }
    }

    private fun buildMoreThanOneCrewMembersOnSameJobLabel(crewMembersWithSameJobList: List<String>): String {
        val authorsBuilder = StringBuilder()
        val size = crewMembersWithSameJobList.size

        for (i in 0 until size - 1) {
            authorsBuilder.append(crewMembersWithSameJobList[i])
            authorsBuilder.append(" / ")
        }

        authorsBuilder.append(crewMembersWithSameJobList[size - 1])

        return authorsBuilder.toString()
    }
}