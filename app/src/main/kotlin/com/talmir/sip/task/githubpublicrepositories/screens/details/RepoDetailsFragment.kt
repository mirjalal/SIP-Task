package com.talmir.sip.task.githubpublicrepositories.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.talmir.sip.task.githubpublicrepositories.databinding.RepoDetailsFragmentBinding

/**
 * A UI controller to show a repository details.
 */
class RepoDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RepoDetailsFragmentBinding.inflate(inflater)

        val repoItem = RepoDetailsFragmentArgs.fromBundle(requireArguments()).repoItem
        val factory = RepoDetailsViewModelFactory(repoItem)
        val viewModel =
            ViewModelProviders.of(this, factory).get(RepoDetailsViewModel::class.java)

        binding.repoDetailsViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}
