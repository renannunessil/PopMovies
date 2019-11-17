package br.com.renannunessil.popmovies.movies.movieslist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.renannunessil.popmovies.R
import br.com.renannunessil.popmovies.ViewModelFactory
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.databinding.FragmentMoviesListBinding
import br.com.renannunessil.popmovies.movies.movieslist.viewmodel.MoviesListViewModel
import br.com.renannunessil.popmovies.movies.sharedviewmodel.SelectedMovieViewModel

class MoviesListFragment : Fragment(), MoviesListAdapter.MoviesAdapterOnClickListener {

    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var moviesListViewModel: MoviesListViewModel
    private lateinit var selectedMovieViewModel: SelectedMovieViewModel

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

        moviesListViewModel =
            ViewModelProviders.of(this, ViewModelFactory(requireContext()))[MoviesListViewModel::class.java]
        selectedMovieViewModel =
            activity?.run {
                ViewModelProviders.of(this, ViewModelFactory(requireContext()))[SelectedMovieViewModel::class.java]
            } ?: throw Exception("Invalid Activity")

        subscribeObservers()
        callMoviesApi()
    }

    private fun callMoviesApi() {
        moviesListViewModel.getPopMovies()
    }

    private fun subscribeObservers() {
        moviesListViewModel.getMoviesListResponseObservable().observe(this, Observer {
            if (it != null) {
                configMoviesListAdapter(it)
            }
        })
    }

    private fun configMoviesListAdapter(moviesList: List<Movie>) {
        val moviesAdapter = MoviesListAdapter(this)
        val layoutManager = LinearLayoutManager(this.requireContext())
        val moviesRecyclerView = binding.rvMoviesList

        moviesAdapter.setData(moviesList)
        moviesRecyclerView.layoutManager = layoutManager
        moviesRecyclerView.adapter = moviesAdapter
    }

    override fun onClick(movie: Movie) {
        selectedMovieViewModel.select(movie)
        Navigation.findNavController(this.requireView()).navigate(R.id.act_moviesList_to_movieInfo)
    }
}