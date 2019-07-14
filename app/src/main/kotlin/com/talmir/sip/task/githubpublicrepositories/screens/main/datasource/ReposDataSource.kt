package com.talmir.sip.task.githubpublicrepositories.screens.main.datasource

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.talmir.sip.task.githubpublicrepositories.network.request.GitHubReposApi
import com.talmir.sip.task.githubpublicrepositories.network.response.models.RepoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class ReposDataSource : PageKeyedDataSource<Int, RepoItem>(), CoroutineScope {
    private val apiService = GitHubReposApi.gitHubReposService

    /**
     * The context of this scope.
     * Context is encapsulated by the scope and used for implementation of coroutine builders that
     * are extensions on the scope. Accessing this property in general code is not recommended for
     * any purposes except accessing the [Job] instance for advanced usages.
     *
     * By convention, should contain an instance of a [job][Job] to enforce structured concurrency.
     */
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    companion object {
        /**
         * A factory function to initialize the [DataSource.Factory] object.
         */
        fun factory() = object : DataSource.Factory<Int, RepoItem?>() {
            override fun create() = ReposDataSource()
        }
    }

    /**
     * Load initial data.
     *
     * This method is called first to initialize a PagedList with data. If it's possible to count
     * the items that can be loaded by the DataSource, it's recommended to pass the loaded data to
     * the callback via the three-parameter [LoadInitialCallback.onResult]. This enables PagedLists
     * presenting data from this source to display placeholders to represent unloaded items.
     *
     * [LoadInitialParams.requestedLoadSize] is a hint, not a requirement, so it may be may be
     * altered or ignored.
     *
     * @param params Parameters for initial load, including requested load size.
     * @param callback Callback that receives initial load data.
     */
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RepoItem>) {
        runBlocking {
            val apiResponse = apiService.getPublicRepos(1)
            when (apiResponse.isSuccessful) {
                true -> {
                    val repoItems = apiResponse.body()?.reposList
                    callback.onResult(repoItems.orEmpty(), null, 2)
                }
                false -> callback.onResult(listOf<RepoItem?>(null), null, null)
            }
        }
    }

    /**
     * Append page with the key specified by [LoadParams.key].
     *
     * It's valid to return a different list size than the page size if it's easier, e.g. if your
     * backend defines page sizes. It is generally safer to increase the number loaded than reduce.
     *
     * Data may be passed synchronously during the load method, or deferred and called at a
     * later time. Further loads going down will be blocked until the callback is called.
     *
     * If data cannot be loaded (for example, if the request is invalid, or the data would be stale
     * and inconsistent, it is valid to call [.invalidate] to invalidate the data source,
     * and prevent further loading.
     *
     * @param params Parameters for the load, including the key for the new page, and requested load
     * size.
     * @param callback Callback that receives loaded data.
     */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepoItem>) {
        launch {
            val apiResponse = apiService.getPublicRepos(params.key)

            when (apiResponse.isSuccessful) {
                true -> {
                    val repoItems = apiResponse.body()?.reposList
                    callback.onResult(repoItems.orEmpty(), params.key.inc())
                }
                false -> callback.onResult(listOf<RepoItem?>(null), null)
            }
        }
    }

    /**
     * Prepend page with the key specified by [LoadParams#key][LoadParams.key].
     *
     * It's valid to return a different list size than the page size if it's easier, e.g. if your
     * backend defines page sizes. It is generally safer to increase the number loaded than reduce.
     *
     * Data may be passed synchronously during the load method, or deferred and called at a
     * later time. Further loads going down will be blocked until the callback is called.
     *
     * If data cannot be loaded (for example, if the request is invalid, or the data would be stale
     * and inconsistent, it is valid to call [invalidate] to invalidate the data source,
     * and prevent further loading.
     *
     * @param params Parameters for the load, including the key for the new page, and requested load
     *               size.
     * @param callback Callback that receives loaded data.
     */
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepoItem>) {  }
}
