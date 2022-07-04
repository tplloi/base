package vn.loitp.app.activity.customviews.imageview.stfaiconImageViewer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.adapter.BaseAdapter
import com.loitpcore.core.utilities.LImageUtil
import kotlinx.android.synthetic.main.view_item_stf.view.*
import vn.loitp.app.R
import vn.loitp.app.activity.customviews.recyclerview.normalRecyclerView.Movie

@LogTag("BookAdapter")
class StfAdapter(
    private val context: Context,
    private val moviesList: MutableList<Movie>,
    private val callback: Callback?
) : BaseAdapter() {

    interface Callback {
        fun onClick(iv: ImageView, movie: Movie, moviesList: MutableList<Movie>, position: Int)
        fun onLongClick(movie: Movie, position: Int)
        fun onLoadMore()
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            itemView.textView.text = movie.title

            val url = movie.cover
            LImageUtil.load(context = context, any = url, imageView = itemView.imageView)

            itemView.rootView.setOnClickListener {
                callback?.onClick(itemView.imageView, movie, moviesList, bindingAdapterPosition)
            }
            itemView.rootView.setOnLongClickListener {
                callback?.onLongClick(movie, bindingAdapterPosition)
                true
            }
            if (bindingAdapterPosition == moviesList.size - 1) {
                callback?.onLoadMore()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_stf, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            holder.bind(moviesList[position])
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}