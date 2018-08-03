package com.kushnir.githhubclient.view.screens.repositoryDetails


import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.RepoDetailsAdapter
import dagger.Module
import dagger.Provides

@Module
class RepoDetailsModule {

    @Provides
    fun providePresenter(): RepoDetailsScreen.Presenter = RepoDetailsPresenter()

    @Provides
    fun provideRepoDetailsAdapter(): RepoDetailsAdapter = RepoDetailsAdapter(mutableListOf())
}