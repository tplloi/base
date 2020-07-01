package vn.loitp.app.activity.customviews.recyclerview.normalrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_movie_list.view.*
import vn.loitp.app.R

class MoviesAdapter(private val moviesList: List<Movie>, private val callback: Callback?) :
        RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    interface Callback {
        fun onClick(movie: Movie, position: Int)
        fun onLongClick(movie: Movie, position: Int)
        fun onLoadMore()
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            itemView.title.text = movie.title
            itemView.genre.text = movie.genre
            itemView.year.text = movie.year
            itemView.rootView.setOnClickListener {
                callback?.onClick(movie = movie, position = bindingAdapterPosition)
            }
            itemView.rootView.setOnLongClickListener {
                callback?.onLongClick(movie = movie, position = bindingAdapterPosition)
                true
            }
            if (bindingAdapterPosition == moviesList.size - 1) {
                callback?.onLoadMore()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_movie_list, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

}