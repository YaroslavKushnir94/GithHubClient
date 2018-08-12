package com.kushnir.githhubclient.view.screens.navigation

import android.os.Parcelable
import com.kushnir.githhubclient.view.screens.repositoryDetails.DetailRepoFragment

class FragmentNavigatorImpl(private val navigationListener: NavigationListener):FragmentNavigator {

    override fun openDetailsRepoFragment(parcelable: Parcelable) {
        navigationListener.showFragment(DetailRepoFragment.newInstance(parcelable))
    }
}