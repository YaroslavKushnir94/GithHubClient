package com.kushnir.githhubclient.view.screens.repositories.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.kushnir.githhubclient.R
import com.kushnir.githhubclient.databinding.RepositoryAdapterItemBinding


class RepositoriesAdapter(private val items: MutableList<RepositoryModel>, private var listener: AdapterClickListener?) : RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RepositoryAdapterItemBinding = DataBindingUtil.inflate(inflater, R.layout.repository_adapter_item, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    fun addItems(items: MutableList<RepositoryModel>) {
        if (items.isEmpty()) return
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addListener(listener: AdapterClickListener) {
        this.listener = listener
    }
}