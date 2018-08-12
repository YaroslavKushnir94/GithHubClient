package com.kushnir.githhubclient.view.base

import android.support.v4.app.Fragment
import com.kushnir.githhubclient.view.screens.navigation.NavigationListener

abstract class BaseFragmentActivity : BaseActivity(), NavigationListener {

    protected abstract fun getFragmentContainer(): Int

    override fun showFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(getFragmentContainer(), fragment, fragment::class.java.name)
        if (addToBackStack) {
            transaction.addToBackStack(fragment::class.java.name)
        }
        transaction.commitAllowingStateLoss()
    }
}