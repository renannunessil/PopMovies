package br.com.renannunessil.popmovies.movies.movieslist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.renannunessil.popmovies.data.models.Movie
import br.com.renannunessil.popmovies.databinding.ItemPopMoviesListBinding
import br.com.renannunessil.popmovies.extension.brDateFormat
import br.com.renannunessil.popmovies.extension.getCalendarFromStringDate
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class MoviesListAdapter(val listener: MoviesAdapterOnClickListener) :
    RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder>() {

    private var moviesList: List<Movie> = ArrayList()

    interface MoviesAdapterOnClickListener {
        fun onClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPopMoviesListBinding.inflate(inflater, parent, false)
        return MoviesListViewHolder(binding)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) =
        holder.bind(moviesList[position])

    fun setData(moviesList: List<Movie>) {
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    inner class MoviesListViewHolder(private val binding: ItemPopMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onClick(moviesList[adapterPosition])
        }

        fun bind(item: Movie) {

            val rbMovieRate = binding.rbMovieRate

            Picasso.get().load(item.getPosterCompletePath()).fit().into(binding.ivMoviePoster)
            binding.tvMovieTitle.text = item.title
            rbMovieRate.rating = item.movieEvaluation/2
            rbMovieRate.stepSize = 0.01F
            rbMovieRate.isEnabled = false
            binding.tvReleaseDate.text = item.releaseDate.getCalendarFromStringDate().brDateFormat()
        }

    }
}