package com.kushnir.githhubclient.view.pagging

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.view.ViewGroup
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryViewHolder
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import com.kushnir.githhubclient.R
import com.kushnir.githhubclient.databinding.RepositoryAdapterItemBinding


class PaggingListAdapter(diffUtil: DiffUtil.ItemCallback<RepositoryModel>) : PagedListAdapter<RepositoryModel, RepositoryViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RepositoryAdapterItemBinding = DataBindingUtil.inflate(inflater, R.layout.repository_adapter_item, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position)!!,null)
    }
}