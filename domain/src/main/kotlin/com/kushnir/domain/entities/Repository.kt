package com.kushnir.domain.entities

data class Repository (
        val id: Long,
        val name: String,
        val fullName: String,
        val description: String,
        val forksCount: Int,
        val owner: Owner
)
data class Owner(
        val id: Long,
        val login: String,
        val avatarUrl: String
)