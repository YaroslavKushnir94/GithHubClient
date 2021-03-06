package com.kushnir.domain.repositories

import com.kushnir.domain.entities.User
import io.reactivex.Observable
import javax.xml.ws.Response

interface UserRepository {
    fun getUserFollowers(userName: String,  page: Int, perPage: Int): Observable<MutableList<User>>
}