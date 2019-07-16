package com.talmir.sip.task.githubpublicrepositories.screens.main.datasource

import androidx.recyclerview.widget.DiffUtil
import com.talmir.sip.task.githubpublicrepositories.network.response.models.RepoItem

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by RecyclerViewAdapter to calculate the minimum number of changes
 * between and old list and a new list that's been passed to adapters' list
 * `submission`.
 */
object PaginationItemDiffCallback : DiffUtil.ItemCallback<RepoItem?>() {
    /**
     * Called to check whether two objects represent the same item.
     *
     * For example, if your items have unique ids, this method should check their id equality.
     *
     * Note: `null` items in the list are assumed to be the same as another `null`
     * item and are assumed to not be the same as a non-`null` item. This callback will
     * not be invoked for either of those cases.
     *
     * @param oldItem The item in the old list.
     * @param newItem The item in the new list.
     * @return True if the two items represent the same object or false if they are different.
     *
     * @see Callback.areItemsTheSame
     */
    override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean =
        // I used referential equality operator - triple equal sign (===) - which returns
        // true if the object references are the same
        oldItem === newItem

    /**
     * Called to check whether two items have the same data.
     *
     * This information is used to detect if the contents of an item have changed.
     *
     * This method to check equality instead of [Object.equals] so that you can
     * change its behavior depending on your UI.
     *
     * For example, if you are using DiffUtil with a
     * [RecyclerView.Adapter], you should
     * return whether the items' visual representations are the same.
     *
     * This method is called only if [.areItemsTheSame] returns `true` for
     * these items.
     *
     * Note: Two `null` items are assumed to represent the same contents. This callback
     * will not be invoked for this case.
     *
     * @param oldItem The item in the old list.
     * @param newItem The item in the new list.
     * @return True if the contents of the items are the same or false if they are different.
     *
     * @see Callback.areContentsTheSame
     */
    override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem) = oldItem.id == newItem.id
}
