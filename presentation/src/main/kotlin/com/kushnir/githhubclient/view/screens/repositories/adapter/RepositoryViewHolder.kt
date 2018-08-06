package com.kushnir.githhubclient.view.screens.repositories.adapter

import android.support.v7.widget.RecyclerView
import com.kushnir.githhubclient.databinding.RepositoryAdapterItemBinding


class RepositoryViewHolder(private val binding: RepositoryAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RepositoryModel, listener: AdapterClickListener?) {
        if (listener != null) {
            binding.listener = listener
        }
        binding.repositoryModel = item
        binding.executePendingBindings()
    }
}