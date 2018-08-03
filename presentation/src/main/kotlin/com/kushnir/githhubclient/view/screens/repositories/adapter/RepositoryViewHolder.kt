package com.kushnir.githhubclient.view.screens.repositories.adapter

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.kushnir.githhubclient.R
import com.kushnir.githhubclient.databinding.RepositoryAdapterItemBinding
import com.squareup.picasso.Picasso


class RepositoryViewHolder(private val binding: RepositoryAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        @BindingAdapter("url")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_github)
                    .into(view)
        }
    }

     fun bind(item: RepositoryModel, listener: AdapterClickListener?) {
        if (listener != null) {
            binding.listener = listener
        }
        binding.repositoryModel = item
        binding.executePendingBindings()
    }
}