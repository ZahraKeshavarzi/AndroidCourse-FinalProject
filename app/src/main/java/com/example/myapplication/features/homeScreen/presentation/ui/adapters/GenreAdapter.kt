package com.example.myapplication.features.homeScreen.presentation.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemGenreBinding
import com.example.myapplication.features.homeScreen.domain.data.model.Genre


class GenreAdapter(private val genres: List<Genre>) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun bindData(genre: Genre) {
            binding.genreButton.text = genre.genreName
            binding.genreButton.typeface = Typeface.DEFAULT_BOLD
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int = genres.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(genres[position])
    }
}