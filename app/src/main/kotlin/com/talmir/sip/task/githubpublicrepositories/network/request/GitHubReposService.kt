package com.talmir.sip.task.githubpublicrepositories.network.request

import com.talmir.sip.task.githubpublicrepositories.network.response.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A public interface that exposes the [getPublicRepos] function.
 */
internal interface GitHubReposService {
    @GET("repositories?q=is:public&per_page=10")
    suspend fun getPublicRepos(@Query("page") pageNumber: Int): retrofit2.Response<Response>
}

/**
 * Holds an object reference of [GitHubReposService] to consume the API.
 */
object GitHubReposApi {
    internal val gitHubReposService: GitHubReposService by lazy {
        retrofit.create(GitHubReposService::class.java)
    }
}
