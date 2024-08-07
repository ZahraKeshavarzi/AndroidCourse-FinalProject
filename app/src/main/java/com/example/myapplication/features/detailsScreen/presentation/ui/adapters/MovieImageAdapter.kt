package com.example.myapplication.features.detailsScreen.presentation.ui.adapters


import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemImageBinding
import com.example.myapplication.features.detailsScreen.FullScreenImageActivity


class MovieImageAdapter(private val movieImages: List<String>) :
    RecyclerView.Adapter<MovieImageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(movieImages[position])
    }

    override fun getItemCount(): Int = movieImages.size


    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun bindData(item: String) {
            Glide.with(binding.MovieImage.context)
                .load(item)
                .into(binding.MovieImage)

            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, FullScreenImageActivity::class.java).apply {
                    putExtra("IMAGE_URL", item)
                }
                context.startActivity(intent)
            }
        }
    }
}