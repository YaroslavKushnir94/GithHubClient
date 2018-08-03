package com.kushnir.githhubclient.view.screens.repositoryDetails.adapter

import com.kushnir.domain.entities.User

class UserModelMapper {
    companion object {
        fun transform(items: MutableList<User>): MutableList<UserModel> {
            val resultList: MutableList<UserModel> = mutableListOf()
            for (item: User in items) {
                resultList.add(transform(item))
            }
            return resultList
        }

        private fun transform(item: User): UserModel {
            return UserModel(item.login,item.avatarUrl)
        }
    }
}