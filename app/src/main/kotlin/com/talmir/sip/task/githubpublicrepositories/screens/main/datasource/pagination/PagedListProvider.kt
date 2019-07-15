package com.talmir.sip.task.githubpublicrepositories.screens.main.datasource.pagination

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * I decided to abstract conf part into this provider,
 * in order to bridge data layer which will benefit in
 * avoiding boring refactoring in the future...
 */
interface PagedListProvider<Value> {
    /**
     * Provide an observable [PagedList] item.
     */
    fun provide(): LiveData<PagedList<Value>>
}
