package com.kushnir.data.entities

import com.kushnir.domain.entities.Owner
import com.kushnir.domain.entities.Repository

class RepositoryEntityMapper {
    companion object {

        fun transform(response: RepositoryResponseEntity?): MutableList<Repository> {
            if (response!!.items == null) throw IllegalArgumentException("Repositories collection is Null")
            val listResult: MutableList<Repository> = mutableListOf()
            for (item: RepositoryEntity in response.items!!) {//TODO fix it
                listResult.add(transform(item))
            }
            return listResult
        }

        private fun transform(repository: RepositoryEntity): Repository {
            return Repository(repository.id, repository.name ?: "", repository.fullName
                    ?: "", repository.description ?: "", repository.forksCount,
                    Owner(repository.owner.id, repository.owner.login
                            ?: "", repository.owner.avatarUrl ?: ""))
        }
    }
}