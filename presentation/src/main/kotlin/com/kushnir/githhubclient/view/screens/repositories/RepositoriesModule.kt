package com.kushnir.githhubclient.view.screens.repositories

import com.kushnir.domain.interactor.GetRepositories
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoriesAdapter
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun providePresenter(getRepositories: GetRepositories): RepositoriesScreen.Presenter {
        return RepositoriesPresenter(getRepositories)
    }

    @Provides
    fun provideAdapter(): RepositoriesAdapter = RepositoriesAdapter(mutableListOf())

}