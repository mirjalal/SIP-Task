package com.talmir.sip.task.githubpublicrepositories.di.components

import android.app.Application
import com.talmir.sip.task.githubpublicrepositories.App
import com.talmir.sip.task.githubpublicrepositories.di.modules.ActivityModule
import com.talmir.sip.task.githubpublicrepositories.di.modules.FragmentModule
import com.talmir.sip.task.githubpublicrepositories.di.modules.PagedListProviderImplModule
import com.talmir.sip.task.githubpublicrepositories.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    ActivityModule::class,
    FragmentModule::class,
    ViewModelModule::class,
    PagedListProviderImplModule::class,
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class
])
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
