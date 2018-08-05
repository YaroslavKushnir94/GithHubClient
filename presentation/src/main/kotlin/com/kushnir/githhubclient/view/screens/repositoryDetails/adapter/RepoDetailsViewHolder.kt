package com.kushnir.githhubclient.view.screens.repositoryDetails.adapter

import android.support.v7.widget.RecyclerView
import com.kushnir.githhubclient.databinding.DetailsAdapterItemBinding

class RepoDetailsViewHolder(private val binding:DetailsAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: UserModel) {
        binding.model = item
    }
}