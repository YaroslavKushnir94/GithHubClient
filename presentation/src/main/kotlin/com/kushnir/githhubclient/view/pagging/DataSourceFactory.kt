package com.kushnir.githhubclient.view.pagging

import android.arch.paging.DataSource
import com.kushnir.domain.interactor.GetRepositories
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel

class DataSourceFactory(
        private val getRepositories: GetRepositories,
        private val params: GetRepositories.Params
) : DataSource.Factory<Int, RepositoryModel>() {
    override fun create(): DataSource<Int, RepositoryModel> {
       return UserDataSource(getRepositories, params)
    }
}