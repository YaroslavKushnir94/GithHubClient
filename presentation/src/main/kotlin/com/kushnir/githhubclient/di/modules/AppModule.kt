package com.kushnir.githhubclient.di.modules

import android.content.Context
import com.example.yaroslav.gallerycotlin.domain.executor.PostExecutionThread
import com.kushnir.data.api.github.repositories.RepositoryApiProvider
import com.kushnir.data.api.github.user.UserApiProvider
import com.kushnir.data.repositories.CommonDataRepository
import com.kushnir.data.repositories.UserDataRepository
import com.kushnir.domain.interactor.GetRepositories
import com.kushnir.domain.repositories.CommonRepository
import com.kushnir.domain.repositories.UserRepository
import com.kushnir.githhubclient.GitHubClient
import com.kushnir.githhubclient.UiThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(app: GitHubClient): Context = app

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }
    @Provides
    fun providesRepository(provider: RepositoryApiProvider): CommonRepository {
        return CommonDataRepository(provider)
    }
    @Provides
    fun providesUserRepository(provider: UserApiProvider): UserRepository {
        return UserDataRepository(provider)
    }

}