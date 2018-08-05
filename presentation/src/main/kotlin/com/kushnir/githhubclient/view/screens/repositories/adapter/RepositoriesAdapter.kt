package com.kushnir.githhubclient.view.screens.repositories.adapter

import android.databinding.DataBindingUtil
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import com.kushnir.githhubclient.R
import com.kushnir.githhubclient.databinding.RepositoryAdapterItemBinding
import com.kushnir.githhubclient.view.screens.ResultTuple


class RepositoriesAdapter(private var items: MutableList<RepositoryModel>) : PagingAdapter<RepositoryViewHolder>() {

    private var listener: AdapterClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RepositoryAdapterItemBinding = DataBindingUtil.inflate(inflater, R.layout.repository_adapter_item, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(items[position],listener)
    }

    fun addItems(result: ResultTuple<MutableList<RepositoryModel>>) {
        if (result.data.isEmpty()) return
        loadFinish()
        if (result.newData) {
            items.clear()
        }
        checkLastPage(result.data.size)
        items.addAll(result.data)
        notifyDataSetChanged()
    }

    fun addClickListener(listener: AdapterClickListener) {
        this.listener = listener
    }

}