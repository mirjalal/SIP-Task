package com.talmir.sip.task.githubpublicrepositories

import com.talmir.sip.task.githubpublicrepositories.network.request.GitHubReposApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    /**
     * Test retrofit service.
     */
    @Test
    fun request_isSucceeds() {
        runBlocking {
            println("page size: ${GitHubReposApi.gitHubReposService.getPublicRepos(1).body()?.reposList?.size}")
        }
    }
}
