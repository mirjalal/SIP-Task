package com.talmir.sip.task.githubpublicrepositories.screens.main.datasource.pagination

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.talmir.sip.task.githubpublicrepositories.network.response.models.RepoItem

/**
 * The implementation of [PagedListProvider].
 */
class PagedListProviderImpl(private val factory: DataSource.Factory<Int, RepoItem?>) :
    PagedListProvider<RepoItem?> {

    /**
     * Provides list to set recycler view's adapter.
     */
    override fun provide(): LiveData<PagedList<RepoItem?>> {
        return LivePagedListBuilder(
            factory, PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .build()
        )//.setFetchExecutor(createFetchExecutor())
            .build()
    }

//    private fun createFetchExecutor(): ExecutorService {
//        val threads = Runtime.getRuntime().availableProcessors() + 1
//        return Executors.newFixedThreadPool(threads)
//    }
}
