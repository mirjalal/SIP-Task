package com.talmir.sip.task.githubpublicrepositories.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.talmir.sip.task.githubpublicrepositories.R
import com.talmir.sip.task.githubpublicrepositories.databinding.ReposListFragmentBinding
import com.talmir.sip.task.githubpublicrepositories.screens.main.datasource.ReposDataSource
import com.talmir.sip.task.githubpublicrepositories.screens.main.datasource.pagination.PagedListProviderImpl
import com.talmir.sip.task.githubpublicrepositories.utils.GitHubRepositoriesStatus

/**
 * A UI controller to show GitHub public repositories
 * to user as list.
 */
class ReposListFragment : Fragment() {
    /**
     * Initialization of the [ReposListFragment] layout.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = ReposListFragmentBinding.inflate(inflater)

        val viewModelFactory = ReposListViewModelFactory(PagedListProviderImpl(ReposDataSource.factory()))
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ReposListViewModel::class.java)

        binding.reposListViewModel = viewModel
        // very-very important!!
        binding.lifecycleOwner = this

        viewModel.observePagedList(this)

        viewModel.gitHubRepositoriesStatus.observe(this, Observer {
            when (it) {
                GitHubRepositoriesStatus.LOADING -> {
                    binding.state.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_loading_animation_large, 0, 0)
                    binding.state.visibility = View.VISIBLE
                    binding.state.text = ""
                }
                GitHubRepositoriesStatus.ERROR -> {
                    binding.state.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_connection_error, 0, 0)
                    binding.state.visibility = View.VISIBLE
                    binding.state.text = getString(R.string.no_internet_connection)
                }
                GitHubRepositoriesStatus.DONE -> {binding.state.visibility = View.GONE}
            }
        })

        viewModel.repoItemToPass.observe(this, Observer {
            if (it != null) {
                findNavController().navigate(
                    ReposListFragmentDirections.actionReposListFragmentToRepoDetailsFragment(it)
                )
                viewModel.displayRepoItemDetailsComplete()
            }
        })

        return binding.root
    }
}
