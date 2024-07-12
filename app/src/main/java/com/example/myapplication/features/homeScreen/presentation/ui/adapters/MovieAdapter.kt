package com.example.myapplication.features.homeScreen.presentation.ui.adapters

import MovieResponse
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemMovieBinding

class MovieAdapter(
    private val items: List<MovieResponse.MovieItem>
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        holder.bind(data)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieResponse.MovieItem) {

            Glide.with(binding.root.context)
                .load(item.poster)
                .into(binding.moviePoster)

            binding.movieTitle.text = item.title
            binding.movieRating.text = item.imdbRating
            binding.movieCountry.text = item.country
            binding.movieYear.text = item.year




            binding.root.setOnClickListener {
                // Handle item click
            }
        }
    }
}
