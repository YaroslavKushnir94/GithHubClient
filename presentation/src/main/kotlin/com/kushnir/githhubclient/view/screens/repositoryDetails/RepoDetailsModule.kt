package com.kushnir.githhubclient.view.screens.repositoryDetails

import com.kushnir.data.api.github.user.UserApiProvider
import com.kushnir.data.repositories.UserDataRepository
import com.kushnir.domain.interactor.GetUserFollowers
import com.kushnir.domain.repositories.UserRepository
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