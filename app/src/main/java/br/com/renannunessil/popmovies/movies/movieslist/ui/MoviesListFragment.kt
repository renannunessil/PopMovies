package br.com.renannunessil.popmovies.movies.movieslist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.renannunessil.popmovies.R
import br.com.renannunessil.popmovies.ViewModelFactory
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.databinding.FragmentMoviesListBinding
import br.com.renannunessil.popmovies.movies.activity.MoviesActivity
import br.com.renannunessil.popmovies.movies.movieslist.viewmodel.MoviesListViewModel
import br.com.renannunessil.popmovies.movies.sharedviewmodel.SelectedMovieViewModel
import kotlinx.android.synthetic.main.fragment_movies_list.*
import java.util.concurrent.atomic.AtomicBoolean

class MoviesListFragment : Fragment(), MoviesListAdapter.MoviesAdapterOnClickListener {

    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var moviesListViewModel: MoviesListViewModel
    private lateinit var selectedMovieViewModel: SelectedMovieViewModel
    private lateinit var parentActivity: MoviesActivity

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

        parentActivity = activity as MoviesActivity

        moviesListViewModel =
            ViewModelProviders.of(this, ViewModelFactory(requireContext(), parentActivity.isTest.get()))[MoviesListViewModel::class.java]
        selectedMovieViewModel =
            activity?.run {
                ViewModelProviders.of(this, ViewModelFactory(requireContext(), parentActivity.isTest.get()))[SelectedMovieViewModel::class.java]
            } ?: throw Exception("Invalid Activity")

        subscribeObservers()
        callMoviesApi()
    }

    private fun callMoviesApi() {
        showLoading(true)
        moviesListViewModel.getPopMovies()
    }

    private fun subscribeObservers() {
        moviesListViewModel.getMoviesListResponseObservable().observe(this, Observer {
            if (it != null) {
                showLoading(false)
                configMoviesListAdapter(it)
            }
        })
        moviesListViewModel.getErrorObservable().observe(this, Observer {
            showLoading(false)
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
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

    fun showLoading(show: Boolean) {
        parentActivity.showLoading(show)
        rv_movies_list.isEnabled = !show
    }
}