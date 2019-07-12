package com.talmir.sip.task.githubpublicrepositories.screens.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.talmir.sip.task.githubpublicrepositories.R

class ReposListFragment : Fragment() {

    companion object {
        fun newInstance() = ReposListFragment()
    }

    private lateinit var viewModel: ReposListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repos_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReposListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
