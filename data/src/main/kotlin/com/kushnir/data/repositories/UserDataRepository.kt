package com.kushnir.data.repositories

import com.kushnir.data.api.github.user.UserApiProvider
import com.kushnir.data.entities.UserEntityMapper
import com.kushnir.domain.entities.User
import com.kushnir.domain.repositories.UserRepository
import io.reactivex.Observable
import retrofit2.Response

class UserDataRepository constructor(private val userApiProvider: UserApiProvider) : UserRepository {

    override fun getUserFollowers(userName: String,  page: Int, perPage: Int): Observable<MutableList<User>> {
        return userApiProvider.getUserFollowers(userName,page,perPage)
                .map { UserEntityMapper.transform(it.body()) }
    }
}