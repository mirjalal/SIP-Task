package com.talmir.sip.task.githubpublicrepositories.di.modules

import com.talmir.sip.task.githubpublicrepositories.screens.details.RepoDetailsFragment
import com.talmir.sip.task.githubpublicrepositories.screens.main.ReposListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeReposListFragment(): ReposListFragment

//    @ContributesAndroidInjector
//    abstract fun contributeRepoDetailsFragment(): RepoDetailsFragment
}