package com.example.myapplication.features.homeScreen.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemPosterssliderBinding
import com.example.myapplication.features.homeScreen.domain.data.model.MovieData

class PostersSliderAdapter(
    private val items: List<MovieData>
) : RecyclerView.Adapter<PostersSliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPosterssliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemPosterssliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(movieData: MovieData) {
            Glide.with(binding.moviePoster.context)
                .load(movieData.poster)
                .into(binding.moviePoster)
            binding.movieTitle.text = movieData.title
            binding.rating.text = movieData.imdbRating
            binding.country.text = movieData.country
            binding.year.text = movieData.year

            binding.root.setOnClickListener {
            }
        }
    }
}