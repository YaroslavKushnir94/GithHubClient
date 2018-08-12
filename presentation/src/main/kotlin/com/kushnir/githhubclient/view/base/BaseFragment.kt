package com.kushnir.githhubclient.view.base

import android.content.Context
import android.support.v4.app.Fragment
import com.kushnir.githhubclient.view.screens.navigation.FragmentNavigator
import com.kushnir.githhubclient.view.screens.navigation.FragmentNavigatorImpl
import com.kushnir.githhubclient.view.screens.navigation.NavigationListener
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    protected lateinit var navigator: FragmentNavigator


    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        addNavigation(context)
    }

    private fun addNavigation(context: Context?) {
        context as? NavigationListener
                ?: throw IllegalArgumentException("Your activity must inheritance " +
                        "BaseFragmentActivity or impl NavigationListener")
        navigator = FragmentNavigatorImpl(context)
    }
}