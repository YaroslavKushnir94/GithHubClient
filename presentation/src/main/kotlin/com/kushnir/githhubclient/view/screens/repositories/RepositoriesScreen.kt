package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.Observer
import com.kushnir.githhubclient.view.screens.repositories.adapter.AdapterClickListener
import com.kushnir.githhubclient.view.base.BaseScreen

interface RepositoriesScreen {
    interface View : BaseScreen.View, AdapterClickListener {
        fun onDataChanged(state: RepositoriesStateModel)
    }

    interface Presenter : BaseScreen.Presenter<View, RepositoriesViewModel>, Observer<RepositoriesStateModel> {
        fun searchTextChanged(key: String)
    }

}