package com.kushnir.githhubclient.di.modules

import com.kushnir.githhubclient.view.screens.repositories.RepositoriesActivity
import com.kushnir.githhubclient.view.screens.repositories.RepositoriesModule
import com.kushnir.githhubclient.view.screens.repositoryDetails.RepoDetailsActivity
import com.kushnir.githhubclient.view.screens.repositoryDetails.RepoDetailsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [RepositoriesModule::class])
    abstract fun bindRepositoriesActivity(): RepositoriesActivity

    @ContributesAndroidInjector(modules = [RepoDetailsModule::class])
    abstract fun bindRepoDetailsActivity(): RepoDetailsActivity
}