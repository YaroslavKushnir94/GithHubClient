package com.kushnir.githhubclient.view.base


interface BaseScreen {
    interface View

    interface Presenter<in T : View> {
        fun onViewCreated(view: T)
    }
}