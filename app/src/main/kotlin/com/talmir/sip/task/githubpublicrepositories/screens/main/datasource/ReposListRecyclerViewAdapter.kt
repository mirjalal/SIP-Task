package com.talmir.sip.task.githubpublicrepositories.screens.main.datasource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.talmir.sip.task.githubpublicrepositories.databinding.RepoDetailsFragmentBinding
import com.talmir.sip.task.githubpublicrepositories.databinding.ReposListItemBinding
import com.talmir.sip.task.githubpublicrepositories.network.response.models.RepoItem

/**
 * The adapter class that holds items for [RecyclerView].
 *
 * As you got, the task requires a `paged recycler view`
 * to get data from network immediately as user scrolls.
 * Thus, I decided to use [PagedListAdapter] instead of
 * [RecyclerView.Adapter] or [ListAdapter] or smth similar
 * to these. Cheers! ^_^
 */
class ReposListRecyclerViewAdapter(private val itemClickListener: RepoItemClickListener,
                                   diffCallback: DiffUtil.ItemCallback<RepoItem?>) :
    PagedListAdapter<RepoItem, ReposListRecyclerViewAdapter.ViewHolder>(diffCallback) {

    /**
     * Called when recycler view request for the `layout` to show.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    /**
     * Called just after the [onCreateViewHolder] invocation to bind
     * the data to pre-initialized view.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), itemClickListener)

    /**
     * A standard view holder class to hold the views ~_~
     */
    class ViewHolder private constructor(private val binding: ReposListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds [repoItem] object to layout file...
         */
        fun bind(repoItem: RepoItem?, itemClickListener: RepoItemClickListener) {
            // sets the properties in the binding class
            binding.repoItem = repoItem
            binding.repoItemClickListener = itemClickListener

            /**
             * Causes the properties updates to execute immediately.
             * Since I'm calling [bind] from [onBindViewHolder] having
             * the bindings execute immediately. As a practice can prevent
             * the recycler view from having to perform extra calculations
             * when it figures out how to display the list.
             */
            binding.executePendingBindings()
        }

        companion object {
            /**
             * Inflate required layout file and pass it to [ViewHolder] as view.
             */
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ReposListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * A click listener class to handle repository items' clicks
 */
class RepoItemClickListener(private val repoItemClickListener: (repoItem: RepoItem) -> Unit) {
    /**
     * A function that will be fired on recycler view each item's click.
     */
    fun onClick(repoItem: RepoItem) = repoItemClickListener(repoItem)
}
