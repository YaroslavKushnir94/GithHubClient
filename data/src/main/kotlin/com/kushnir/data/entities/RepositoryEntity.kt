package com.kushnir.data.entities

import com.google.gson.annotations.SerializedName

data class RepositoryEntity(
        val id: Long,
        val name: String?,
        @SerializedName("full_name")
        val fullName: String?,
        val description: String?,
        @SerializedName("forks_count")
        val forksCount: Int,
        val owner: OwnerEntity
)

data class OwnerEntity(
        val id: Long,
        val login: String?,
        @SerializedName("avatar_url")
        val avatarUrl: String?
)