package com.kushnir.domain.interactor

import com.example.yaroslav.gallerycotlin.domain.executor.PostExecutionThread
import com.kushnir.domain.entities.User
import com.kushnir.domain.repositories.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetUserFollowers @Inject constructor(private val userRepository: UserRepository, postExecutionThread: PostExecutionThread) : UseCase<MutableList<User>, GetUserFollowers.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<MutableList<User>> {
        return userRepository.getUserFollowers(params.name,params.page,params.perPage)
    }

    data class Params(val name: String, val page: Int, val perPage: Int)
}
