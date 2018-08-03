package com.kushnir.githhubclient.view.base

import android.arch.lifecycle.ViewModel

interface BaseScreen {
    interface View

    interface Presenter<in T : View, in M : ViewModel> {
        fun onViewCreated(view: T, model: M)
    }
}