package com.example.myapplication.features.favoritesScreen.presentation.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemMovieBinding
import com.example.myapplication.features.detailsScreen.DetailsScreenActivity
import com.example.myapplication.features.detailsScreen.domain.data.model.MovieDetailsData

class FavoriteMoviesAdapter (
    private val movieItems: List<MovieDetailsData>
) : RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder>() {

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
        holder.bind(movieItems[position])
    }

    override fun getItemCount() = movieItems.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieDetailsData) {

            Glide.with(binding.moviePoster.context)
                .load(item.poster)
                .into(binding.moviePoster)

            binding.movieTitle.text = item.title
            binding.movieRating.text = item.imdbRating
            binding.movieCountry.text = item.country
            binding.movieYear.text = item.year

            binding.root.setOnClickListener {
                val context = it.context
                val intent = Intent(context, DetailsScreenActivity::class.java).apply {
                    putExtra("movieId", item.id)
                }
                context.startActivity(intent)

            }
        }
    }
}
