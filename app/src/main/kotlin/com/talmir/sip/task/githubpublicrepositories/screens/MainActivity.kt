package com.talmir.sip.task.githubpublicrepositories.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.talmir.sip.task.githubpublicrepositories.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Not the entry point but main activity of the application
 */
class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    /**
    * Rather than injecting the ViewModelFactory
    * in the activity, we are going to implement the
    * HasActivityInjector and inject the ViewModelFactory
    * into our [ReposListFragment]
    */
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        // IMPORTANT! Make sure this is before calling super.onCreate
//        setTheme(R.style.AppTheme)

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
