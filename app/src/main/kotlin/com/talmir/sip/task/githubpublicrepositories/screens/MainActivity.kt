package com.talmir.sip.task.githubpublicrepositories.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.talmir.sip.task.githubpublicrepositories.R

/**
 * Not the entry point but main activity of the application
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // IMPORTANT! Make sure this is before calling super.onCreate
//        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
