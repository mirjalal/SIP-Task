package com.talmir.sip.task.githubpublicrepositories.network.request

import com.talmir.sip.task.githubpublicrepositories.network.response.models.Response
import retrofit2.http.GET

/**
 * A public interface that exposes the [getPublicRepos] function.
 */
internal interface GitHubReposService {
    @GET("")
    suspend fun getPublicRepos(): retrofit2.Response<Response>
}

object GitHubReposApi {
    internal val gitHubReposService: GitHubReposService by lazy {
        retrofit.create(GitHubReposService::class.java)
    }
}
