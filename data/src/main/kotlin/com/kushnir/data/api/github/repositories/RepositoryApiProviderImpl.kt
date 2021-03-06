package com.kushnir.data.api.github.repositories

import com.kushnir.data.ThreadTransformer
import com.kushnir.data.api.RetrofitConfig
import com.kushnir.data.entities.RepositoryResponseEntity
import io.reactivex.Observable
import retrofit2.Response

class RepositoryApiProviderImpl(config: RetrofitConfig) : RepositoryApiProvider {

    private val repoService = config.retrofit.create(RepositoryService::class.java)

    override fun searchRepositories(key: String,page:Int,perPage:Int): Observable<Response<RepositoryResponseEntity>> {
        return repoService.getRepositories(page, perPage, key)
                .compose(ThreadTransformer<Response<RepositoryResponseEntity>>())
    }
}