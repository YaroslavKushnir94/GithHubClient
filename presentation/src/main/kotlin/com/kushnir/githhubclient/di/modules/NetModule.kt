package com.kushnir.githhubclient.di.modules

import com.kushnir.data.api.github.GithubRetrofitConfig
import com.kushnir.data.api.github.repositories.RepositoryApiProvider
import com.kushnir.data.api.github.repositories.RepositoryApiProviderImpl
import com.kushnir.data.api.github.user.UserApiProvider
import com.kushnir.data.api.github.user.UserApiProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetModule {

    @Singleton
    @Provides
    fun provideGithubApi() = GithubRetrofitConfig()

    @Provides
    fun provideRepositoryApi(config: GithubRetrofitConfig): RepositoryApiProvider = RepositoryApiProviderImpl(config)

    @Provides
    fun provideUserApi(config: GithubRetrofitConfig):UserApiProvider = UserApiProviderImpl(config)

}