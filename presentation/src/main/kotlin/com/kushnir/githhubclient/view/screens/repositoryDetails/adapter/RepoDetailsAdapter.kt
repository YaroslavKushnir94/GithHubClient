package com.kushnir.githhubclient.view.screens.repositoryDetails.adapter

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kushnir.githhubclient.R
import com.kushnir.githhubclient.databinding.DetailsAdapterItemBinding
import com.kushnir.githhubclient.view.base.PagingAdapter

class RepoDetailsAdapter(private val items: MutableList<UserModel>) : PagingAdapter<RepoDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: DetailsAdapterItemBinding = DataBindingUtil.inflate(inflater, R.layout.details_adapter_item, parent, false)
        return RepoDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoDetailsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItems(items: MutableList<UserModel>) {
        if (items.isEmpty()) return
        loadFinish()
        checkLastPage(items.size)
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}