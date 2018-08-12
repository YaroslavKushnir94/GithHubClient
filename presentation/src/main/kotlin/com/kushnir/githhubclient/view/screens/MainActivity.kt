package com.kushnir.githhubclient.view.screens

import android.os.Bundle
import com.kushnir.githhubclient.R
import com.kushnir.githhubclient.view.base.BaseFragmentActivity
import com.kushnir.githhubclient.view.screens.repositories.RepositoriesFragment
import dagger.android.AndroidInjection

class MainActivity : BaseFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            showFragment(RepositoriesFragment.newInstance(),false)
        }
    }

    override fun getFragmentContainer() = R.id.frContainer
}