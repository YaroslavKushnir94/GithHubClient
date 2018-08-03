package com.kushnir.data.api.github.user

import com.kushnir.data.entities.UserEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("users/{username}/followers")
    fun getFollowers(
            @Path("username") userName: String
    ): Observable<Response<List<UserEntity>>>
}