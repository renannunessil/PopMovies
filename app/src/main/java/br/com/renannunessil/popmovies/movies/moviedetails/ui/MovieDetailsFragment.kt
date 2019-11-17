package br.com.renannunessil.popmovies.movies.moviedetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.renannunessil.popmovies.Constants
import br.com.renannunessil.popmovies.ViewModelFactory
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.databinding.FragmentMovieDetailsBinding
import br.com.renannunessil.popmovies.extension.brDateFormat
import br.com.renannunessil.popmovies.extension.getCalendarFromStringDate
import br.com.renannunessil.popmovies.movies.activity.MoviesActivity
import br.com.renannunessil.popmovies.movies.moviedetails.viewmodel.MovieDetailsViewModel
import br.com.renannunessil.popmovies.movies.sharedviewmodel.SelectedMovieViewModel
import com.squareup.picasso.Picasso

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var selectedMovieViewModel: SelectedMovieViewModel
    private lateinit var selectedMovie: Movie
    private lateinit var parentActivity: MoviesActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentActivity = activity as MoviesActivity
        parentActivity.showLoading(true)
        movieDetailsViewModel =
            ViewModelProviders.of(this, ViewModelFactory(requireContext()))[MovieDetailsViewModel::class.java]

        selectedMovieViewModel =
            activity?.run {
                ViewModelProviders.of(this, ViewModelFactory(requireContext()))[SelectedMovieViewModel::class.java]
            } ?: throw Exception("Invalid Activity")

        subscribeObservers()
    }

    private fun subscribeObservers() {
        movieDetailsViewModel.getMovieCreditsResponseObservable().observe(this, Observer {
            if (it != null) {
                selectedMovie.movieCredits = it
                selectedMovieViewModel.select(selectedMovie)
                bindData()
                parentActivity.showLoading(false)
            }
        })

        selectedMovieViewModel.getSelectedMovieObservable().observe(this, Observer<Movie> {
            if (it != null) {
                selectedMovie = it
                movieDetailsViewModel.getMovieCredits(selectedMovie.id)
            }
        })
    }

    private fun bindData() {
        binding.tvMovieDetailsTitle.text = selectedMovie.title
        binding.tvMovieDetailsRate.text = selectedMovie.movieEvaluation.toString()
        binding.tvMovieDetailsReleaseDate.text =
            selectedMovie.releaseDate.getCalendarFromStringDate().brDateFormat()
        binding.tvMovieDetailsAuthor.text = selectedMovieViewModel.getCrewMember(Constants.AUTHOR)
        binding.tvMovieDetailsDirector.text =
            selectedMovieViewModel.getCrewMember(Constants.DIRECTOR)
        Picasso.get().load(selectedMovie.getPosterCompletePath()).fit().into(binding.ivMovieDetailsPoster)
    }

}