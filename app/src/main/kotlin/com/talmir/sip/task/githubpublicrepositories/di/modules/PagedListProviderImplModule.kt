package com.talmir.sip.task.githubpublicrepositories.di.modules

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.talmir.sip.task.githubpublicrepositories.network.response.models.RepoItem
import com.talmir.sip.task.githubpublicrepositories.screens.main.datasource.ReposDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 */
@Module
class PagedListProviderImplModule {
    /**
     *
     */
    @Provides
    @Singleton
    fun providePagedListProvider(): LiveData<PagedList<RepoItem?>> {
        return LivePagedListBuilder(
            ReposDataSource.factory(),
            PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .build()
        ).build()
    }
}
