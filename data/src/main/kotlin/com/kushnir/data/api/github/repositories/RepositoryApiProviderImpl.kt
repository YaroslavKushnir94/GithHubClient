package com.kushnir.data.api.github.repositories

import com.kushnir.data.ThreadTransformer
import com.kushnir.data.api.RetrofitConfig
import com.kushnir.data.entities.RepositoryEntity
import com.kushnir.data.entities.RepositoryResponseEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class RepositoryApiProviderImpl(config: RetrofitConfig) : RepositoryApiProvider {

    private val repoService = config.retrofit.create(RepositoryService::class.java)

    override fun searchRepositories(key: String): Observable<Response<RepositoryResponseEntity>> {
        return repoService.getRepositories(1, 100, key)
                .compose(ThreadTransformer<Response<RepositoryResponseEntity>>())

    }
}