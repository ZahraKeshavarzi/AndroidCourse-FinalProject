package com.example.myapplication.features.homeScreen.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemGenreBinding
import com.example.myapplication.features.homeScreen.domain.data.model.GenreData
import com.example.myapplication.features.homeScreen.presentation.viewmodel.HomeScreenViewModel


class GenreAdapter(private val genreData: List<GenreData>) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(genreData[position])
    }

    override fun getItemCount(): Int = genreData.size


    inner class ViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(genreData: GenreData) {
            binding.genreButton.text = genreData.name
            binding.root.setOnClickListener {
                HomeScreenViewModel.genreId.postValue(genreData.id)
            }
        }
    }
}