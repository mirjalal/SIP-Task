package com.talmir.sip.task.githubpublicrepositories.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talmir.sip.task.githubpublicrepositories.network.response.models.RepoItem
import com.talmir.sip.task.githubpublicrepositories.screens.main.datasource.pagination.PagedListProvider

/**
 * As we know, we cannot create instantiate any [ViewModel]
 * class when it requires a parameter in constructor.
 * Shortly, to pass [pagedListProvider] to [ReposListViewModel]
 * we need this factory class.
 */
class ReposListViewModelFactory(private val pagedListProvider: PagedListProvider<RepoItem?>)
    : ViewModelProvider.Factory {
    /**
     * No more boilerplate code: keep this function simple.
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("unchecked_cast")
        if (modelClass.isAssignableFrom(ReposListViewModel::class.java))
            return ReposListViewModel(pagedListProvider) as T
        throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
    }
}
