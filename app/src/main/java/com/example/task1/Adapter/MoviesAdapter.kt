package com.example.task1.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.Model.Movie
import com.example.task1.R

class MovieAdapter(
    private val movies: List<Movie>,
    private var onClick: (movie: Movie) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nameTextView.setText(movies[position].title)
        viewHolder.idTextView.setText(movies[position].id)
        viewHolder.setOnClickListener(View.OnClickListener { onClick(movies[position]) })
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(private val listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.movie_title)
        val idTextView = itemView.findViewById<TextView>(R.id.movie_id)
        fun setOnClickListener(onClickListener: View.OnClickListener) {
            listItemView.setOnClickListener(onClickListener)
        }
    }
}