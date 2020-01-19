package com.kushnir.githhubclient.view.screens.repositories.adapter

import android.support.v7.util.DiffUtil

class RepositoryModel(
        val fullName: String = "",
        val description: String = "",
        val forksCount: Int = 0,
        val login: String = "",
        val avatarUrl: String? = "") {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RepositoryModel>() {
            override fun areItemsTheSame(oldItem: RepositoryModel?, newItem: RepositoryModel?): Boolean  = false

            override fun areContentsTheSame(oldItem: RepositoryModel?, newItem: RepositoryModel?): Boolean  = false
        }
    }
}