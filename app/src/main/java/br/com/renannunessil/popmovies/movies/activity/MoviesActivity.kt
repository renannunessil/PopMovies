package br.com.renannunessil.popmovies.movies.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.renannunessil.popmovies.R
import br.com.renannunessil.popmovies.movies.movieslist.ui.MoviesListFragment
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
    }

    fun showLoading(show: Boolean) {
        val progressBar = cl_progressbar

        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}
