package com.talmir.sip.task.githubpublicrepositories.network.response.models

import com.google.gson.annotations.SerializedName

/**
 * A model class to that matches with GitHub search API response.
 */
data class Response(
    @SerializedName("items")
    val reposList: List<RepoItem> = listOf()
)
