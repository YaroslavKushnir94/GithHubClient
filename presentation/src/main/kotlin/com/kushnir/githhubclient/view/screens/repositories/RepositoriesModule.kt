package com.kushnir.githhubclient.view.screens.repositories

import android.support.v7.util.DiffUtil
import com.kushnir.data.api.github.repositories.RepositoryApiProvider
import com.kushnir.data.repositories.CommonDataRepository
import com.kushnir.domain.interactor.GetRepositories
import com.kushnir.domain.repositories.CommonRepository
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoriesAdapter
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun providePresenter(): RepositoriesScreen.Presenter {
        return RepositoriesPresenter()
    }

    @Provides
    fun provideAdapter(): RepositoriesAdapter = RepositoriesAdapter(mutableListOf())

}