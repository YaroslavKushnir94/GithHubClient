package com.kushnir.githhubclient.di.modules

import com.kushnir.githhubclient.view.screens.repositories.RepositoriesFragment
import com.kushnir.githhubclient.view.screens.repositories.RepositoriesModule
import com.kushnir.githhubclient.view.screens.repositoryDetails.DetailRepoFragment
import com.kushnir.githhubclient.view.screens.repositoryDetails.RepoDetailsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [RepositoriesModule::class])
    abstract fun bindRepoFragment(): RepositoriesFragment

    @ContributesAndroidInjector(modules = [RepoDetailsModule::class])
    abstract fun bindDetailRepository(): DetailRepoFragment
}