package com.kushnir.data.entities

import com.kushnir.domain.entities.User

class UserEntityMapper {
    companion object {
        fun transform(items: List<UserEntity>?): MutableList<User> {
            if (items == null) throw IllegalArgumentException("Repositories collection is Null")
            val listResult: MutableList<User> = mutableListOf()
            for (item: UserEntity in items) {
                listResult.add(transform(item))
            }
            return listResult
        }

        private fun transform(user: UserEntity): User {
            return User(user.login?:"",user.avatarUrl?:"")
        }
    }
}