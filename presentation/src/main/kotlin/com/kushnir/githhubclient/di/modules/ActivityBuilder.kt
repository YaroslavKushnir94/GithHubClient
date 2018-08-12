package com.kushnir.githhubclient.di.modules

import com.kushnir.githhubclient.view.screens.MainActivity
import com.kushnir.githhubclient.view.screens.repositories.RepositoriesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [RepositoriesModule::class])
    abstract fun bindRepositoriesActivity(): MainActivity
}