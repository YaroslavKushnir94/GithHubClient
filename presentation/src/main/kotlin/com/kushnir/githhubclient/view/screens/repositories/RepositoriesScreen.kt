package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.Observer
import com.kushnir.githhubclient.view.screens.repositories.adapter.AdapterClickListener
import com.kushnir.githhubclient.view.base.BaseScreen
import com.kushnir.githhubclient.view.screens.LiveDataParams
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel

interface RepositoriesScreen {
    interface View : BaseScreen.View, AdapterClickListener {
        fun changeData(state: RepositoriesStateModel)
    }

    interface Presenter : BaseScreen.Presenter<View, RepositoriesViewModel>, Observer<LiveDataParams<RepositoriesStateModel>>{
        fun searchTextChanged(key: String, page:Int)
        fun loadMore(key: String)
    }

}