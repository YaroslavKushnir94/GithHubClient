package com.kushnir.data.api.github.repositories

import com.kushnir.data.entities.RepositoryEntity
import com.kushnir.data.entities.RepositoryResponseEntity
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RepositoryService {
    @GET("/search/repositories")
    fun getRepositories(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("q") keywords: String

    ): Observable<Response<RepositoryResponseEntity>>
}