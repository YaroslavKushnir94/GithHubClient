package com.kushnir.githhubclient.di

import com.kushnir.githhubclient.GitHubClient
import com.kushnir.githhubclient.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class, NetModule::class, FragmentBuilder::class, ViewModelModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: GitHubClient): Builder

        fun build(): AppComponent
    }

    fun inject(app: GitHubClient)

}