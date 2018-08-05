package com.kushnir.githhubclient.view.screens.repositories.adapter

import com.kushnir.githhubclient.view.screens.ResultTuple

interface DataSourceCallback {
    fun load(key: String, page: Int, perPage: Int, result: Result)
    interface Result {
        fun onResult(result: ResultTuple<MutableList<RepositoryModel>>)
    }
}