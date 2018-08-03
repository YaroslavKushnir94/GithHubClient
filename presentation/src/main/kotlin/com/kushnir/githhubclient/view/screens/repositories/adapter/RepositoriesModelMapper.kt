package com.kushnir.githhubclient.view.screens.repositories.adapter

import com.kushnir.domain.entities.Repository

class RepositoriesModelMapper {

    companion object {
        fun transform(items: MutableList<Repository>): MutableList<RepositoryModel> {
            val resultList: MutableList<RepositoryModel> = mutableListOf()
            for (item: Repository in items) {
                resultList.add(transform(item))
            }
            return resultList
        }

        private fun transform(item: Repository): RepositoryModel {
            return RepositoryModel(item.fullName, item.description, item.forksCount, item.owner.login, item.owner.avatarUrl)
        }
    }
}