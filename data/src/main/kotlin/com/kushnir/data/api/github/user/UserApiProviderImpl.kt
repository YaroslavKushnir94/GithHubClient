package com.kushnir.data.api.github.user

import com.kushnir.data.ThreadTransformer
import com.kushnir.data.api.RetrofitConfig
import com.kushnir.data.entities.UserEntity
import io.reactivex.Observable
import retrofit2.Response

class UserApiProviderImpl(config: RetrofitConfig) : UserApiProvider {
    private val userService = config.retrofit.create(UserService::class.java)

    override fun getUserFollowers(userName: String,  page: Int, perPage: Int): Observable<Response<List<UserEntity>>> {
        return userService.getFollowers(userName,page,perPage)
                .compose(ThreadTransformer<Response<List<UserEntity>>>())
    }
}