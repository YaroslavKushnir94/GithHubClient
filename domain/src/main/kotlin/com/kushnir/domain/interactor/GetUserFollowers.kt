package com.kushnir.domain.interactor

import com.example.yaroslav.gallerycotlin.domain.executor.PostExecutionThread
import com.kushnir.domain.entities.User
import com.kushnir.domain.repositories.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetUserFollowers @Inject constructor(private val userRepository: UserRepository, postExecutionThread: PostExecutionThread) : UseCase<MutableList<User>, String>(postExecutionThread) {
    override fun buildUseCaseObservable(params: String): Observable<MutableList<User>> {
        return userRepository.getUserFollowers(params)
    }
}