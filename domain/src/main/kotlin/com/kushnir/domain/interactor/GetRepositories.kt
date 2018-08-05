package com.kushnir.domain.interactor

import com.example.yaroslav.gallerycotlin.domain.executor.PostExecutionThread
import com.kushnir.domain.entities.Repository
import com.kushnir.domain.repositories.CommonRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRepositories @Inject constructor(private val commonRepository: CommonRepository, postExecutionThread: PostExecutionThread) : UseCase<MutableList<Repository>, GetRepositories.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<MutableList<Repository>> {
        return commonRepository.searchRepositories(params.key,params.page,params.perPage)
    }

    data class Params(val key: String, val page:Int, val perPage:Int)
}