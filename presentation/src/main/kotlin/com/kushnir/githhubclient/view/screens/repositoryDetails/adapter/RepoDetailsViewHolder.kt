package com.kushnir.githhubclient.view.screens.repositoryDetails.adapter

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.kushnir.githhubclient.R
import com.kushnir.githhubclient.databinding.DetailsAdapterItemBinding
import com.squareup.picasso.Picasso

class RepoDetailsViewHolder(private val binding:DetailsAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {

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

    fun bind(item: UserModel) {
        binding.model = item
    }
}