package com.kushnir.data.entities

import com.google.gson.annotations.SerializedName

data class UserEntity(val login: String?,
                 @SerializedName("avatar_url")
                 val avatarUrl: String?)
