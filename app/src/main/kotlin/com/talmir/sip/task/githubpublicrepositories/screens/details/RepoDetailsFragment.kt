package com.talmir.sip.task.githubpublicrepositories.screens.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.talmir.sip.task.githubpublicrepositories.R

class RepoDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = RepoDetailsFragment()
    }

    private lateinit var viewModel: RepoDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repo_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RepoDetailsViewModel::class.java)
    }

}
