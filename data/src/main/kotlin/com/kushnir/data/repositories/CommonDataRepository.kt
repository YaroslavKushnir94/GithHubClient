package com.kushnir.data.repositories

import com.kushnir.data.api.github.repositories.RepositoryApiProvider
import com.kushnir.data.entities.RepositoryEntityMapper
import com.kushnir.domain.entities.Repository
import com.kushnir.domain.repositories.CommonRepository
import io.reactivex.Observable

class CommonDataRepository constructor(private val provider: RepositoryApiProvider) : CommonRepository {

    override fun searchRepositories(key: String, page: Int, perPage: Int): Observable<MutableList<Repository>> {
        return provider.searchRepositories(key, page, perPage)
                .map { RepositoryEntityMapper.transform(it.body()) }
    }
}