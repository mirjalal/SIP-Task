package com.talmir.sip.task.githubpublicrepositories.network.response.models

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean = false,
    val items: List<Item> = listOf(),
    @SerializedName("total_count")
    val totalCount: Int = 0
)