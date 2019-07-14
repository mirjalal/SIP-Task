package com.talmir.sip.task.githubpublicrepositories.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talmir.sip.task.githubpublicrepositories.network.response.models.RepoItem

/**
 * ViewModel for [RepoDetailsFragment].
 */
class RepoDetailsViewModel(repositoryItem: RepoItem) : ViewModel() {

    private val _repoItem = MutableLiveData<RepoItem>()
    val repoItem: LiveData<RepoItem>
        get() = _repoItem

    init {
        _repoItem.value = repositoryItem
    }
}
