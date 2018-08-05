package com.kushnir.domain.repositories

import com.kushnir.domain.entities.Repository
import io.reactivex.Observable

interface CommonRepository {
    fun searchRepositories( key: String,  page:Int, perPage:Int): Observable<MutableList<Repository>>
}