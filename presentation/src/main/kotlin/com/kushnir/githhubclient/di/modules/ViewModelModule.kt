package com.kushnir.githhubclient.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kushnir.githhubclient.view.screens.MyViewModelFactory
import com.kushnir.githhubclient.view.screens.repositories.RepositoriesViewModel
import com.kushnir.githhubclient.view.screens.repositoryDetails.RepoDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindRepoDetailsViewModel(vieModel: RepositoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel:RepoDetailViewModel):ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

}