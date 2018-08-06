package com.kushnir.githhubclient.di

import com.kushnir.githhubclient.GitHubClient
import com.kushnir.githhubclient.di.modules.ActivityBuilder
import com.kushnir.githhubclient.di.modules.AppModule
import com.kushnir.githhubclient.di.modules.NetModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ActivityBuilder::class, NetModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: GitHubClient): Builder

        fun build(): AppComponent
    }

    fun inject(app: GitHubClient)

}