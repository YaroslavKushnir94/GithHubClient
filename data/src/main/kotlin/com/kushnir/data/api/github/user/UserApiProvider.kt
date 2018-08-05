package com.kushnir.data.api.github.user

import com.kushnir.data.entities.UserEntity
import io.reactivex.Observable
import retrofit2.Response

interface UserApiProvider {
     fun getUserFollowers(userName: String,  page: Int, perPage: Int): Observable<Response<List<UserEntity>>>
}