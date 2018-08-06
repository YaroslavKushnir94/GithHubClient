package com.kushnir.data.api.github.repositories

import com.kushnir.data.entities.RepositoryResponseEntity
import io.reactivex.Observable
import retrofit2.Response

interface RepositoryApiProvider {
    fun searchRepositories(key: String,page:Int,perPage:Int): Observable<Response<RepositoryResponseEntity>>
}