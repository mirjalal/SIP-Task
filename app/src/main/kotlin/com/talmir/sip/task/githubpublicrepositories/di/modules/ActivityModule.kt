package com.talmir.sip.task.githubpublicrepositories.di.modules

import com.talmir.sip.task.githubpublicrepositories.screens.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * An abstract class that helps to connect
 * activity and fragment(s) using DI.
 */
@Module
abstract class ActivityModule {
    /**
     * We modify our [ActivityModule] by adding the
     * FragmentModule to the Activity which contains
     * the fragment.
     */
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
