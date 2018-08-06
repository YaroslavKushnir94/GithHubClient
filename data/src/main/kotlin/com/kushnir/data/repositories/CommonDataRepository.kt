package com.kushnir.data.repositories

import com.kushnir.data.api.github.repositories.RepositoryApiProvider
import com.kushnir.data.entities.RepositoryEntityMapper
import com.kushnir.data.exception.NetworkException
import com.kushnir.data.utils.Connectivity
import com.kushnir.domain.entities.Repository
import com.kushnir.domain.repositories.CommonRepository
import io.reactivex.Observable

class CommonDataRepository constructor(private val provider: RepositoryApiProvider, private val connectivity: Connectivity) : CommonRepository {

    override fun searchRepositories(key: String, page: Int, perPage: Int): Observable<MutableList<Repository>> {
        if (!connectivity.isConnected()) {
            return Observable.error(NetworkException())
        }
        return provider.searchRepositories(key, page, perPage)
                .map { RepositoryEntityMapper.transform(it.body()) }
    }
}