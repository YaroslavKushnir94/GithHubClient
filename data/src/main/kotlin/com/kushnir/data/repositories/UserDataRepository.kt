package com.kushnir.data.repositories

import com.kushnir.data.api.github.user.UserApiProvider
import com.kushnir.data.entities.UserEntityMapper
import com.kushnir.data.exception.NetworkException
import com.kushnir.data.utils.Connectivity
import com.kushnir.domain.entities.User
import com.kushnir.domain.repositories.UserRepository
import io.reactivex.Observable

class UserDataRepository constructor(private val userApiProvider: UserApiProvider, private val connectivity: Connectivity) : UserRepository {

    override fun getUserFollowers(userName: String, page: Int, perPage: Int): Observable<MutableList<User>> {
        if (!connectivity.isConnected()) {
            return Observable.error(NetworkException())
        }
        return userApiProvider.getUserFollowers(userName, page, perPage)
                .map { UserEntityMapper.transform(it.body()) }
    }
}
