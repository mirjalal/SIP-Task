package com.talmir.sip.task.githubpublicrepositories.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talmir.sip.task.githubpublicrepositories.network.response.models.RepoItem

/**
 * A helper factory class to pass [repoItem] to [RepoDetailsViewModel] instance.
 */
class RepoDetailsViewModelFactory(private val repoItem: RepoItem) : ViewModelProvider.Factory {
    /**
     * Overridden method to customize what should be happen.
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("unchecked_cast")
        if (modelClass.isAssignableFrom(RepoDetailsViewModel::class.java))
            return RepoDetailsViewModel(repoItem) as T
        throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
    }
}
