package br.com.renannunessil.popmovies.movies.movieslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.renannunessil.popmovies.ViewModelFactory
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.databinding.FragmentMoviesListBinding
import br.com.renannunessil.popmovies.movies.movieslist.viewmodel.MoviesListViewModel

class MoviesListFragment : Fragment() {

    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var moviesListViewModel: MoviesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMoviesListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesListViewModel = ViewModelProviders.of(this, ViewModelFactory()) [MoviesListViewModel::class.java]

        subscribe()
    }

    private fun subscribe() {
        moviesListViewModel.getMoviesListResponseObservable().observe(this, Observer {
            if (it != null) {
                configMoviesListAdapter(it)
            }
        })
    }

    private fun configMoviesListAdapter(moviesList: List<Movie>) {
        TODO("Implementar Adapter")
    }
}