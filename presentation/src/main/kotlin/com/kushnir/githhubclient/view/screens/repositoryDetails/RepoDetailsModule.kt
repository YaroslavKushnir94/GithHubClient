package com.kushnir.githhubclient.view.screens.repositoryDetails


import com.kushnir.domain.interactor.GetUserFollowers
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.RepoDetailsAdapter
import dagger.Module
import dagger.Provides

@Module
class RepoDetailsModule {

    @Provides
    fun providePresenter(getUserFollowers: GetUserFollowers): RepoDetailsScreen.Presenter = RepoDetailsPresenter(getUserFollowers)

    @Provides
    fun provideRepoDetailsAdapter(): RepoDetailsAdapter = RepoDetailsAdapter(mutableListOf())
}