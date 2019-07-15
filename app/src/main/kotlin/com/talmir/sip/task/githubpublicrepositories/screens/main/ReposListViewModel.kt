package com.talmir.sip.task.githubpublicrepositories.screens.main

import androidx.lifecycle.*
import com.talmir.sip.task.githubpublicrepositories.network.response.models.RepoItem
import com.talmir.sip.task.githubpublicrepositories.screens.main.datasource.RepoItemClickListener
import com.talmir.sip.task.githubpublicrepositories.screens.main.datasource.ReposListRecyclerViewAdapter
import com.talmir.sip.task.githubpublicrepositories.screens.main.datasource.pagination.PagedListProvider
import com.talmir.sip.task.githubpublicrepositories.screens.main.datasource.pagination.PaginationItemDiffCallback
import com.talmir.sip.task.githubpublicrepositories.utils.GitHubRepositoriesStatus

/**
 * ViewModel for [ReposListFragment].
 */
class ReposListViewModel(pagedListProvider: PagedListProvider<RepoItem?>) : ViewModel() {

    private val pagedListData = pagedListProvider.provide()

    private val _repoItemToPass = MutableLiveData<RepoItem>()
    /**
     * Will be used in [ReposListFragment] to navigate
     * [RepoDetailsFragment] with bundle parameters.
     */
    val repoItemToPass: LiveData<RepoItem>
        get() = _repoItemToPass

    fun displayRepoItemDetailsComplete() {
        _repoItemToPass.value = null
    }

    private val repoItemClickListener = RepoItemClickListener {
        _repoItemToPass.value = it
    }

    /**
     * An adapter to be attached to recycler view
     */
    val adapter = ReposListRecyclerViewAdapter(repoItemClickListener, PaginationItemDiffCallback)

    private val _gitHubRepositoriesStatus = MutableLiveData<Int>()

    /**
     * because of [_gitHubRepositoriesStatus] is mutable
     * (i.e. could be changed from elsewhere if it is not private),
     * I used immutable `version` of it.
     */
    val gitHubRepositoriesStatus: LiveData<Int>
        get() = _gitHubRepositoriesStatus

    /**
     * Set initial value as "LOADING"...
     */
    init {
        _gitHubRepositoriesStatus.value = GitHubRepositoriesStatus.LOADING
    }

    /**
     * A simple function to observe the [pagedListData] changes.
     */
    fun observePagedList(owner: LifecycleOwner) {
        pagedListData.observe(owner, Observer {
            adapter.submitList(it)
            /**
             * Look [ReposDataSource.loadInitial] and [ReposDataSource.loadAfter]
             * method to understand why I wrote this if statement.
             */
            if (it.isEmpty()) {
                _gitHubRepositoriesStatus.value = GitHubRepositoriesStatus.LOADING
            } else {
                if (it[0] != null)
                    _gitHubRepositoriesStatus.value = GitHubRepositoriesStatus.DONE
                else {
                    adapter.submitList(null) // VERY IMPORTANT!
                    _gitHubRepositoriesStatus.value = GitHubRepositoriesStatus.ERROR
                }
            }
        })
    }
}
