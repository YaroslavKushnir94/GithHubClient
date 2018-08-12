package com.kushnir.githhubclient.view.screens.navigation

import android.support.v4.app.Fragment

interface NavigationListener {
    fun showFragment( fragment:Fragment,addToBackStack:Boolean = true)
}