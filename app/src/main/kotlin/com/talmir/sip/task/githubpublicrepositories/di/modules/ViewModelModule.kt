package com.talmir.sip.task.githubpublicrepositories.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talmir.sip.task.githubpublicrepositories.screens.details.RepoDetailsViewModel
import com.talmir.sip.task.githubpublicrepositories.screens.main.ReposListViewModel
import com.talmir.sip.task.githubpublicrepositories.di.utils.ViewModelFactory
import com.talmir.sip.task.githubpublicrepositories.di.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /**
     * This method basically says inject this object
     * into a [Map] using the [IntoMap] annotation,
     * with the [ReposListViewModel] as key, and a [Provider]
     * that will build a [ReposListViewModel] object.
     */
    @Binds
    @IntoMap
    @ViewModelKey(ReposListViewModel::class)
    protected abstract fun reposListViewModel(reposListViewModel: ReposListViewModel): ViewModel

    /**
     * This method basically says inject this object
     * into a [Map] using the [IntoMap] annotation,
     * with the [RepoDetailsViewModel] as key, and a [Provider]
     * that will build a [RepoDetailsViewModel] object.
     */
//    @Binds
//    @IntoMap
//    @ViewModelKey(RepoDetailsViewModel::class)
//    protected abstract fun repoDetailsViewModel(reposListViewModel: RepoDetailsViewModel): ViewModel
}
