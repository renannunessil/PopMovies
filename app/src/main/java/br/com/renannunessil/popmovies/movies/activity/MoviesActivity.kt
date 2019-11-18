package br.com.renannunessil.popmovies.movies.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.renannunessil.popmovies.Constants
import br.com.renannunessil.popmovies.R
import kotlinx.android.synthetic.main.activity_movies.*
import java.util.concurrent.atomic.AtomicBoolean

class MoviesActivity : AppCompatActivity() {

    val isTest = AtomicBoolean()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        if (intent?.extras?.getBoolean(Constants.TEST_PARAMETHER) == null) {
            isTest.set(false)
        } else {
            isTest.set(intent.extras.getBoolean(Constants.TEST_PARAMETHER))
        }
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
