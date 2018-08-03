package com.kushnir.data.api.github.repositories

import com.kushnir.data.entities.RepositoryEntity
import com.kushnir.data.entities.RepositoryResponseEntity
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response

interface RepositoryApiProvider {
    fun searchRepositories(key: String): Observable<Response<RepositoryResponseEntity>>

}