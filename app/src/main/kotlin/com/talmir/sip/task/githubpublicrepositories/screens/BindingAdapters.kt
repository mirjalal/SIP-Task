package com.talmir.sip.task.githubpublicrepositories.screens

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.talmir.sip.task.githubpublicrepositories.R
import com.talmir.sip.task.githubpublicrepositories.network.response.models.RepoItem
import com.talmir.sip.task.githubpublicrepositories.screens.main.ReposListViewModel

/**
 * A helper method to bind [ReposListViewModel.adapter] to recycler view.
 *
 * WARNING: apply-plugin: 'kotlin-kapt' required in build.gradle (app level) file!
 */
@BindingAdapter("adapter")
fun RecyclerView.setAdapter(viewModel: ReposListViewModel?) {
    viewModel?.let {
        adapter = it.adapter
    }
}

@BindingAdapter("avatar")
fun ImageView.setAvatar(repoItem: RepoItem?) {
    repoItem?.let {
        Glide.with(context)
            .load(it.owner.avatar)
            .placeholder(R.drawable.ic_loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(this)
    }
}

@BindingAdapter("textAndVisibility")
fun TextView.setTextAndVisibility(value: String?) {
    value?.let {
        text = context.getString(R.string.repository_url).format(it)
    }
}
