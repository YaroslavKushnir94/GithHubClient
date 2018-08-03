package com.kushnir.data.entities

import com.google.gson.annotations.SerializedName

data class RepositoryResponseEntity(
        @SerializedName("total_count")
        var totalCount: Int,
        @SerializedName("incomplete_results")
        var incompleteResults: Boolean,
        var items: MutableList<RepositoryEntity>? = null)