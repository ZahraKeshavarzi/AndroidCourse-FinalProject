package com.example.myapplication.features.searchScreen.presentation.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemMovieBinding
import com.example.myapplication.features.detailsScreen.DetailsScreenActivity
import com.example.myapplication.features.homeScreen.domain.data.model.MovieData

class MovieSearchAdapter(
    private val movieItems: List<MovieData>
) : RecyclerView.Adapter<MovieSearchAdapter.ViewHolder>() {

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
        setAnimation(holder.itemView)
    }

    private fun setAnimation(itemView: View) {
        val context = itemView.context
        val animation = AnimationUtils.loadAnimation(context, R.anim.recycleritem_animation)
        itemView.startAnimation(animation)
    }

    override fun getItemCount() = movieItems.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieData) {

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
